// Autogenerated from Pigeon (v1.0.12), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package me.vilsol.eu_cert;

import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression"})
public class Pigeon {

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class NameData {
    private String Forename;
    public String getForename() { return Forename; }
    public void setForename(String setterArg) { this.Forename = setterArg; }

    private String Surname;
    public String getSurname() { return Surname; }
    public void setSurname(String setterArg) { this.Surname = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("Forename", Forename);
      toMapResult.put("Surname", Surname);
      return toMapResult;
    }
    static NameData fromMap(Map<String, Object> map) {
      NameData fromMapResult = new NameData();
      Object Forename = map.get("Forename");
      fromMapResult.Forename = (String)Forename;
      Object Surname = map.get("Surname");
      fromMapResult.Surname = (String)Surname;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class VaccinationEntry {
    private String TargetAgent;
    public String getTargetAgent() { return TargetAgent; }
    public void setTargetAgent(String setterArg) { this.TargetAgent = setterArg; }

    private String VaccineOrProphylaxis;
    public String getVaccineOrProphylaxis() { return VaccineOrProphylaxis; }
    public void setVaccineOrProphylaxis(String setterArg) { this.VaccineOrProphylaxis = setterArg; }

    private String VaccineMedicalProduct;
    public String getVaccineMedicalProduct() { return VaccineMedicalProduct; }
    public void setVaccineMedicalProduct(String setterArg) { this.VaccineMedicalProduct = setterArg; }

    private String MarketingAuthorizationHolder;
    public String getMarketingAuthorizationHolder() { return MarketingAuthorizationHolder; }
    public void setMarketingAuthorizationHolder(String setterArg) { this.MarketingAuthorizationHolder = setterArg; }

    private Long DoseNumber;
    public Long getDoseNumber() { return DoseNumber; }
    public void setDoseNumber(Long setterArg) { this.DoseNumber = setterArg; }

    private Long TotalDoses;
    public Long getTotalDoses() { return TotalDoses; }
    public void setTotalDoses(Long setterArg) { this.TotalDoses = setterArg; }

    private String DateOfVaccination;
    public String getDateOfVaccination() { return DateOfVaccination; }
    public void setDateOfVaccination(String setterArg) { this.DateOfVaccination = setterArg; }

    private String CountryOfVaccination;
    public String getCountryOfVaccination() { return CountryOfVaccination; }
    public void setCountryOfVaccination(String setterArg) { this.CountryOfVaccination = setterArg; }

    private String CertificateIssuer;
    public String getCertificateIssuer() { return CertificateIssuer; }
    public void setCertificateIssuer(String setterArg) { this.CertificateIssuer = setterArg; }

    private String UVCI;
    public String getUVCI() { return UVCI; }
    public void setUVCI(String setterArg) { this.UVCI = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("TargetAgent", TargetAgent);
      toMapResult.put("VaccineOrProphylaxis", VaccineOrProphylaxis);
      toMapResult.put("VaccineMedicalProduct", VaccineMedicalProduct);
      toMapResult.put("MarketingAuthorizationHolder", MarketingAuthorizationHolder);
      toMapResult.put("DoseNumber", DoseNumber);
      toMapResult.put("TotalDoses", TotalDoses);
      toMapResult.put("DateOfVaccination", DateOfVaccination);
      toMapResult.put("CountryOfVaccination", CountryOfVaccination);
      toMapResult.put("CertificateIssuer", CertificateIssuer);
      toMapResult.put("UVCI", UVCI);
      return toMapResult;
    }
    static VaccinationEntry fromMap(Map<String, Object> map) {
      VaccinationEntry fromMapResult = new VaccinationEntry();
      Object TargetAgent = map.get("TargetAgent");
      fromMapResult.TargetAgent = (String)TargetAgent;
      Object VaccineOrProphylaxis = map.get("VaccineOrProphylaxis");
      fromMapResult.VaccineOrProphylaxis = (String)VaccineOrProphylaxis;
      Object VaccineMedicalProduct = map.get("VaccineMedicalProduct");
      fromMapResult.VaccineMedicalProduct = (String)VaccineMedicalProduct;
      Object MarketingAuthorizationHolder = map.get("MarketingAuthorizationHolder");
      fromMapResult.MarketingAuthorizationHolder = (String)MarketingAuthorizationHolder;
      Object DoseNumber = map.get("DoseNumber");
      fromMapResult.DoseNumber = (DoseNumber == null) ? null : ((DoseNumber instanceof Integer) ? (Integer)DoseNumber : (Long)DoseNumber);
      Object TotalDoses = map.get("TotalDoses");
      fromMapResult.TotalDoses = (TotalDoses == null) ? null : ((TotalDoses instanceof Integer) ? (Integer)TotalDoses : (Long)TotalDoses);
      Object DateOfVaccination = map.get("DateOfVaccination");
      fromMapResult.DateOfVaccination = (String)DateOfVaccination;
      Object CountryOfVaccination = map.get("CountryOfVaccination");
      fromMapResult.CountryOfVaccination = (String)CountryOfVaccination;
      Object CertificateIssuer = map.get("CertificateIssuer");
      fromMapResult.CertificateIssuer = (String)CertificateIssuer;
      Object UVCI = map.get("UVCI");
      fromMapResult.UVCI = (String)UVCI;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class TestEntry {
    private String TargetAgent;
    public String getTargetAgent() { return TargetAgent; }
    public void setTargetAgent(String setterArg) { this.TargetAgent = setterArg; }

    private String TestType;
    public String getTestType() { return TestType; }
    public void setTestType(String setterArg) { this.TestType = setterArg; }

    private String TestName;
    public String getTestName() { return TestName; }
    public void setTestName(String setterArg) { this.TestName = setterArg; }

    private String TestManufacturer;
    public String getTestManufacturer() { return TestManufacturer; }
    public void setTestManufacturer(String setterArg) { this.TestManufacturer = setterArg; }

    private String SampleCollected;
    public String getSampleCollected() { return SampleCollected; }
    public void setSampleCollected(String setterArg) { this.SampleCollected = setterArg; }

    private String TestResult;
    public String getTestResult() { return TestResult; }
    public void setTestResult(String setterArg) { this.TestResult = setterArg; }

    private String TestingCentre;
    public String getTestingCentre() { return TestingCentre; }
    public void setTestingCentre(String setterArg) { this.TestingCentre = setterArg; }

    private String CountryOfTest;
    public String getCountryOfTest() { return CountryOfTest; }
    public void setCountryOfTest(String setterArg) { this.CountryOfTest = setterArg; }

    private String CertificateIssuer;
    public String getCertificateIssuer() { return CertificateIssuer; }
    public void setCertificateIssuer(String setterArg) { this.CertificateIssuer = setterArg; }

    private String UVCI;
    public String getUVCI() { return UVCI; }
    public void setUVCI(String setterArg) { this.UVCI = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("TargetAgent", TargetAgent);
      toMapResult.put("TestType", TestType);
      toMapResult.put("TestName", TestName);
      toMapResult.put("TestManufacturer", TestManufacturer);
      toMapResult.put("SampleCollected", SampleCollected);
      toMapResult.put("TestResult", TestResult);
      toMapResult.put("TestingCentre", TestingCentre);
      toMapResult.put("CountryOfTest", CountryOfTest);
      toMapResult.put("CertificateIssuer", CertificateIssuer);
      toMapResult.put("UVCI", UVCI);
      return toMapResult;
    }
    static TestEntry fromMap(Map<String, Object> map) {
      TestEntry fromMapResult = new TestEntry();
      Object TargetAgent = map.get("TargetAgent");
      fromMapResult.TargetAgent = (String)TargetAgent;
      Object TestType = map.get("TestType");
      fromMapResult.TestType = (String)TestType;
      Object TestName = map.get("TestName");
      fromMapResult.TestName = (String)TestName;
      Object TestManufacturer = map.get("TestManufacturer");
      fromMapResult.TestManufacturer = (String)TestManufacturer;
      Object SampleCollected = map.get("SampleCollected");
      fromMapResult.SampleCollected = (String)SampleCollected;
      Object TestResult = map.get("TestResult");
      fromMapResult.TestResult = (String)TestResult;
      Object TestingCentre = map.get("TestingCentre");
      fromMapResult.TestingCentre = (String)TestingCentre;
      Object CountryOfTest = map.get("CountryOfTest");
      fromMapResult.CountryOfTest = (String)CountryOfTest;
      Object CertificateIssuer = map.get("CertificateIssuer");
      fromMapResult.CertificateIssuer = (String)CertificateIssuer;
      Object UVCI = map.get("UVCI");
      fromMapResult.UVCI = (String)UVCI;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class RecoveryEntry {
    private String TargetAgent;
    public String getTargetAgent() { return TargetAgent; }
    public void setTargetAgent(String setterArg) { this.TargetAgent = setterArg; }

    private String FirstPositiveResult;
    public String getFirstPositiveResult() { return FirstPositiveResult; }
    public void setFirstPositiveResult(String setterArg) { this.FirstPositiveResult = setterArg; }

    private String DateValidFrom;
    public String getDateValidFrom() { return DateValidFrom; }
    public void setDateValidFrom(String setterArg) { this.DateValidFrom = setterArg; }

    private String DateValidUntil;
    public String getDateValidUntil() { return DateValidUntil; }
    public void setDateValidUntil(String setterArg) { this.DateValidUntil = setterArg; }

    private String CountryOfTest;
    public String getCountryOfTest() { return CountryOfTest; }
    public void setCountryOfTest(String setterArg) { this.CountryOfTest = setterArg; }

    private String CertificateIssuer;
    public String getCertificateIssuer() { return CertificateIssuer; }
    public void setCertificateIssuer(String setterArg) { this.CertificateIssuer = setterArg; }

    private String UVCI;
    public String getUVCI() { return UVCI; }
    public void setUVCI(String setterArg) { this.UVCI = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("TargetAgent", TargetAgent);
      toMapResult.put("FirstPositiveResult", FirstPositiveResult);
      toMapResult.put("DateValidFrom", DateValidFrom);
      toMapResult.put("DateValidUntil", DateValidUntil);
      toMapResult.put("CountryOfTest", CountryOfTest);
      toMapResult.put("CertificateIssuer", CertificateIssuer);
      toMapResult.put("UVCI", UVCI);
      return toMapResult;
    }
    static RecoveryEntry fromMap(Map<String, Object> map) {
      RecoveryEntry fromMapResult = new RecoveryEntry();
      Object TargetAgent = map.get("TargetAgent");
      fromMapResult.TargetAgent = (String)TargetAgent;
      Object FirstPositiveResult = map.get("FirstPositiveResult");
      fromMapResult.FirstPositiveResult = (String)FirstPositiveResult;
      Object DateValidFrom = map.get("DateValidFrom");
      fromMapResult.DateValidFrom = (String)DateValidFrom;
      Object DateValidUntil = map.get("DateValidUntil");
      fromMapResult.DateValidUntil = (String)DateValidUntil;
      Object CountryOfTest = map.get("CountryOfTest");
      fromMapResult.CountryOfTest = (String)CountryOfTest;
      Object CertificateIssuer = map.get("CertificateIssuer");
      fromMapResult.CertificateIssuer = (String)CertificateIssuer;
      Object UVCI = map.get("UVCI");
      fromMapResult.UVCI = (String)UVCI;
      return fromMapResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static class CertificatePayload {
    private String Version;
    public String getVersion() { return Version; }
    public void setVersion(String setterArg) { this.Version = setterArg; }

    private NameData Name;
    public NameData getName() { return Name; }
    public void setName(NameData setterArg) { this.Name = setterArg; }

    private String DateOfBirth;
    public String getDateOfBirth() { return DateOfBirth; }
    public void setDateOfBirth(String setterArg) { this.DateOfBirth = setterArg; }

    private List<VaccinationEntry> Vaccinations;
    public List<VaccinationEntry> getVaccinations() { return Vaccinations; }
    public void setVaccinations(List<VaccinationEntry> setterArg) { this.Vaccinations = setterArg; }

    private List<TestEntry> Tests;
    public List<TestEntry> getTests() { return Tests; }
    public void setTests(List<TestEntry> setterArg) { this.Tests = setterArg; }

    private List<RecoveryEntry> Recoveries;
    public List<RecoveryEntry> getRecoveries() { return Recoveries; }
    public void setRecoveries(List<RecoveryEntry> setterArg) { this.Recoveries = setterArg; }

    Map<String, Object> toMap() {
      Map<String, Object> toMapResult = new HashMap<>();
      toMapResult.put("Version", Version);
      toMapResult.put("Name", (Name == null) ? null : Name.toMap());
      toMapResult.put("DateOfBirth", DateOfBirth);
      toMapResult.put("Vaccinations", Vaccinations);
      toMapResult.put("Tests", Tests);
      toMapResult.put("Recoveries", Recoveries);
      return toMapResult;
    }
    static CertificatePayload fromMap(Map<String, Object> map) {
      CertificatePayload fromMapResult = new CertificatePayload();
      Object Version = map.get("Version");
      fromMapResult.Version = (String)Version;
      Object Name = map.get("Name");
      fromMapResult.Name = NameData.fromMap((Map)Name);
      Object DateOfBirth = map.get("DateOfBirth");
      fromMapResult.DateOfBirth = (String)DateOfBirth;
      Object Vaccinations = map.get("Vaccinations");
      fromMapResult.Vaccinations = (List<VaccinationEntry>)Vaccinations;
      Object Tests = map.get("Tests");
      fromMapResult.Tests = (List<TestEntry>)Tests;
      Object Recoveries = map.get("Recoveries");
      fromMapResult.Recoveries = (List<RecoveryEntry>)Recoveries;
      return fromMapResult;
    }
  }
  private static class DecoderAPICodec extends StandardMessageCodec {
    public static final DecoderAPICodec INSTANCE = new DecoderAPICodec();
    private DecoderAPICodec() {}
    @Override
    protected Object readValueOfType(byte type, ByteBuffer buffer) {
      switch (type) {
        case (byte)128:         
          return CertificatePayload.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)129:         
          return NameData.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)130:         
          return RecoveryEntry.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)131:         
          return TestEntry.fromMap((Map<String, Object>) readValue(buffer));
        
        case (byte)132:         
          return VaccinationEntry.fromMap((Map<String, Object>) readValue(buffer));
        
        default:        
          return super.readValueOfType(type, buffer);
        
      }
    }
    @Override
    protected void writeValue(ByteArrayOutputStream stream, Object value)     {
      if (value instanceof CertificatePayload) {
        stream.write(128);
        writeValue(stream, ((CertificatePayload) value).toMap());
      } else 
      if (value instanceof NameData) {
        stream.write(129);
        writeValue(stream, ((NameData) value).toMap());
      } else 
      if (value instanceof RecoveryEntry) {
        stream.write(130);
        writeValue(stream, ((RecoveryEntry) value).toMap());
      } else 
      if (value instanceof TestEntry) {
        stream.write(131);
        writeValue(stream, ((TestEntry) value).toMap());
      } else 
      if (value instanceof VaccinationEntry) {
        stream.write(132);
        writeValue(stream, ((VaccinationEntry) value).toMap());
      } else 
{
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter.*/
  public interface DecoderAPI {
    CertificatePayload decode(byte[] data);

    /** The codec used by DecoderAPI. */
    static MessageCodec<Object> getCodec() {
      return DecoderAPICodec.INSTANCE;
    }

    /** Sets up an instance of `DecoderAPI` to handle messages through the `binaryMessenger`. */
    static void setup(BinaryMessenger binaryMessenger, DecoderAPI api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(binaryMessenger, "dev.flutter.pigeon.DecoderAPI.decode", getCodec());
        if (api != null) {
          channel.setMessageHandler((message, reply) -> {
            Map<String, Object> wrapped = new HashMap<>();
            try {
              ArrayList<Object> args = (ArrayList<Object>)message;
              byte[] dataArg = (byte[])args.get(0);
              if (dataArg == null) {
                throw new NullPointerException("dataArg unexpectedly null.");
              }
              CertificatePayload output = api.decode(dataArg);
              wrapped.put("result", output);
            }
            catch (Error | RuntimeException exception) {
              wrapped.put("error", wrapError(exception));
            }
            reply.reply(wrapped);
          });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
  private static Map<String, Object> wrapError(Throwable exception) {
    Map<String, Object> errorMap = new HashMap<>();
    errorMap.put("message", exception.toString());
    errorMap.put("code", exception.getClass().getSimpleName());
    errorMap.put("details", null);
    return errorMap;
  }
}
