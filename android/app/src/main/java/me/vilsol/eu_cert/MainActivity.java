package me.vilsol.eu_cert;

import androidx.annotation.NonNull;

import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import nl.minvws.encoding.Base45;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.upokecenter.cbor.CBORObject;
import com.upokecenter.cbor.CBORType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends FlutterActivity implements Pigeon.DecoderAPI {

    private static final int MAGIC_OUTER_KEY = -260;
    private static final int MAGIC_INNER_KEY = 1;

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        Pigeon.DecoderAPI.setup(flutterEngine.getDartExecutor().getBinaryMessenger(), this);
    }

    @Override
    public Pigeon.CertificatePayload decode(byte[] data) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        int[] pixels = convertBitmapToByteArray(bitmap);
        RGBLuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), pixels);
        BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();

        try {
            Result decode = reader.decode(bitmap1);
            byte[] decoded = Base45.getDecoder().decode(decode.getText().substring(4).getBytes());
            byte[] decompressed = decompress(decoded);

            CBORObject outerObject = CBORObject.DecodeFromBytes(decompressed);
            if (outerObject.getType() == CBORType.Array && outerObject.size() >= 3) {
                CBORObject innerObject = CBORObject.DecodeFromBytes(outerObject.get(2).GetByteString());
                if (innerObject.getType() == CBORType.Map) {
                    CBORObject outerMap = innerObject.get(MAGIC_OUTER_KEY);
                    CBORObject innerMap = outerMap.get(MAGIC_INNER_KEY);

                    Pigeon.CertificatePayload payload = new Pigeon.CertificatePayload();
                    payload.setVersion(innerMap.get("ver").AsString());
                    payload.setDateOfBirth(innerMap.get("dob").AsString());
                    payload.setName(new Pigeon.NameData());
                    payload.getName().setForename(innerMap.get("nam").get("gn").AsString());
                    payload.getName().setSurname(innerMap.get("nam").get("fn").AsString());

                    CBORObject vaccinationGroup = innerMap.get("v");
                    if (vaccinationGroup != null) {
                        payload.setVaccinations(new ArrayList<>());
                        for (int i = 0; i < vaccinationGroup.size(); i++) {
                            CBORObject vaccinationEntry = vaccinationGroup.get(i);
                            Pigeon.VaccinationEntry entry = new Pigeon.VaccinationEntry();
                            entry.setTargetAgent(vaccinationEntry.get("tg").AsString());
                            entry.setVaccineOrProphylaxis(vaccinationEntry.get("vp").AsString());
                            entry.setVaccineMedicalProduct(vaccinationEntry.get("mp").AsString());
                            entry.setMarketingAuthorizationHolder(vaccinationEntry.get("ma").AsString());
                            entry.setDoseNumber((long) vaccinationEntry.get("dn").AsInt32());
                            entry.setTotalDoses((long) vaccinationEntry.get("sd").AsInt32());
                            entry.setDateOfVaccination(vaccinationEntry.get("dt").AsString());
                            entry.setCountryOfVaccination(vaccinationEntry.get("co").AsString());
                            entry.setCertificateIssuer(vaccinationEntry.get("is").AsString());
                            entry.setUVCI(vaccinationEntry.get("ci").AsString());
                            payload.getVaccinations().add(entry);
                        }
                    }

                    CBORObject testGroup = innerMap.get("t");
                    if (testGroup != null) {
                        payload.setTests(new ArrayList<>());
                        for (int i = 0; i < testGroup.size(); i++) {
                            CBORObject testEntry = testGroup.get(i);
                            Pigeon.TestEntry entry = new Pigeon.TestEntry();
                            entry.setTargetAgent(testEntry.get("tg").AsString());
                            entry.setTestType(testEntry.get("tt").AsString());
                            entry.setTestName(testEntry.get("nm").AsString());
                            entry.setTestManufacturer(testEntry.get("ma").AsString());
                            entry.setSampleCollected(testEntry.get("sc").AsString());
                            entry.setTestResult(testEntry.get("tr").AsString());
                            entry.setTestingCentre(testEntry.get("tc").AsString());
                            entry.setCountryOfTest(testEntry.get("co").AsString());
                            entry.setCertificateIssuer(testEntry.get("is").AsString());
                            entry.setUVCI(testEntry.get("ci").AsString());
                            payload.getTests().add(entry);
                        }
                    }

                    CBORObject recoveryGroup = innerMap.get("r");
                    if (recoveryGroup != null) {
                        payload.setRecoveries(new ArrayList<>());
                        for (int i = 0; i < recoveryGroup.size(); i++) {
                            CBORObject recoveryEntry = recoveryGroup.get(i);
                            Pigeon.RecoveryEntry entry = new Pigeon.RecoveryEntry();
                            entry.setTargetAgent(recoveryEntry.get("tg").AsString());
                            entry.setFirstPositiveResult(recoveryEntry.get("fr").AsString());
                            entry.setDateValidFrom(recoveryEntry.get("df").AsString());
                            entry.setDateValidUntil(recoveryEntry.get("du").AsString());
                            entry.setCountryOfTest(recoveryEntry.get("co").AsString());
                            entry.setCertificateIssuer(recoveryEntry.get("is").AsString());
                            entry.setUVCI(recoveryEntry.get("ci").AsString());
                        }
                    }

                    return payload;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int[] convertBitmapToByteArray(Bitmap bitmap) {
        IntBuffer buffer = IntBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(buffer);
        buffer.rewind();
        return buffer.array();
    }

    public byte[] decompress(byte[] data) {
        int rem = data.length;

        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);

        byte[] outbuf = new byte[1024];

        Inflater decompressor = new Inflater();
        decompressor.setInput(data);
        while (rem > 0) {
            if (decompressor.finished()) {
                decompressor = new Inflater();
                int offset = data.length - rem;
                decompressor.setInput(data, offset, rem);
            }

            try {
                int count = decompressor.inflate(outbuf, 0, outbuf.length);
                rem = decompressor.getRemaining();
                bos.write(outbuf, 0, count);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bos.toByteArray();
    }

}
