import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:file_picker/file_picker.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:get_storage/get_storage.dart';

import 'pigeon.dart';

void main() async {
  await GetStorage.init();
  runApp(const EUCertificate());
}

class EUCertificate extends StatelessWidget {
  const EUCertificate({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'EU Certificate',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const HomePage(),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  DecoderAPI decoderAPI = DecoderAPI();

  File? qrFile;
  CertificatePayload? certificateData;

  _HomePageState() {
    if (GetStorage().hasData("qrFile")) {
      qrFile = File(GetStorage().read("qrFile"));
      _processCertificate();
    }
  }

  Future<void> _getCertificate() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(type: FileType.custom, allowedExtensions: ['jpg', 'jpeg', 'png']);

    if (result != null && result.files.single.path != null) {
      File file = File(result.files.single.path!);

      setState(() {
        qrFile = file;
        certificateData = null;
      });

      GetStorage().write("qrFile", file.path);
      await _processCertificate();
    }
  }

  Future<void> _processCertificate() async {
    if (qrFile == null) {
      setState(() {
        certificateData = null;
      });
      return;
    }

    try {
      var certificatePayload = await decoderAPI.decode(qrFile!.readAsBytesSync());

      setState(() {
        certificateData = certificatePayload;
      });
    } on PlatformException catch (e) {
      if (kDebugMode) {
        print(e.message);
      }
    }
  }

  void handleClick(int item) {
    switch (item) {
      case 0:
        _getCertificate();
        break;
      case 1:
        if (certificateData != null) {
          showDialog(
              context: context,
              builder: (BuildContext context) {
                return AlertDialog(
                  title: const Text("Info"),
                  content: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      Text.rich(TextSpan(children: [
                        const TextSpan(text: 'Forename: ', style: TextStyle(fontWeight: FontWeight.bold)),
                        TextSpan(text: certificateData!.Name!.Forename!)
                      ])),
                      Text.rich(TextSpan(children: [
                        const TextSpan(text: 'Surname: ', style: TextStyle(fontWeight: FontWeight.bold)),
                        TextSpan(text: certificateData!.Name!.Surname!)
                      ])),
                      Text.rich(TextSpan(children: [
                        const TextSpan(text: 'Date of Birth: ', style: TextStyle(fontWeight: FontWeight.bold)),
                        TextSpan(text: certificateData!.DateOfBirth!)
                      ])),
                      Text.rich(TextSpan(children: [
                        const TextSpan(text: 'Version: ', style: TextStyle(fontWeight: FontWeight.bold)),
                        TextSpan(text: certificateData!.Version!)
                      ])),
                      if (certificateData!.Vaccinations != null) ...[
                        ...certificateData!.Vaccinations!
                            .map((entry) {
                              return [
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'Disease or agent targeted:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry!.TargetAgent!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Vaccine or prophylaxis:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.VaccineOrProphylaxis!),
                                ]),
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'Vaccine medicinal product:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.VaccineMedicalProduct!),
                                ]),
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'Marketing Authorization Holder:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.MarketingAuthorizationHolder!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Dose Number:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.DoseNumber!.toString()),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Total Series of Doses:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.TotalDoses!.toString()),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Date of Vaccination:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.DateOfVaccination!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Country of Vaccination:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.CountryOfVaccination!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Certificate Issuer:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.CertificateIssuer!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(
                                      text: 'Unique Certificate Identifier, UVCI:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.UVCI!),
                                ]),
                              ];
                            })
                            .expand((i) => i)
                            .toList()
                      ],
                      if (certificateData!.Tests != null) ...[
                        const Text(''),
                        ...certificateData!.Tests!
                            .map((entry) {
                              return [
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'Disease or agent targeted:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry!.TargetAgent!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Type of Test:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.TestType!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'NAA Test Name:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.TestName!),
                                ]),
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'RAT Test name and manufacturer:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.TestManufacturer!),
                                ]),
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'Date/Time of Sample Collection:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.SampleCollected!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Test Result:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.TestResult!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Testing Centre:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.TestingCentre!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Country of Test:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.CountryOfTest!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Certificate Issuer:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.CertificateIssuer!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(
                                      text: 'Unique Certificate Identifier, UVCI:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.UVCI!),
                                ]),
                              ];
                            })
                            .expand((i) => i)
                            .toList()
                      ],
                      if (certificateData!.Recoveries != null) ...[
                        const Text(''),
                        ...certificateData!.Recoveries!
                            .map((entry) {
                              return [
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'Disease or agent targeted:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry!.TargetAgent!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(
                                      text: 'Date of first positive NAA test result:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.FirstPositiveResult!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Certificate Valid From:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.DateValidFrom!),
                                ]),
                                Column(children: [
                                  const Text.rich(
                                      TextSpan(text: 'Certificate Valid Until:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.DateValidUntil!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Country of Test:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.CountryOfTest!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(text: 'Certificate Issuer:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.CertificateIssuer!),
                                ]),
                                Column(children: [
                                  const Text.rich(TextSpan(
                                      text: 'Unique Certificate Identifier, UVCI:', style: TextStyle(fontWeight: FontWeight.bold))),
                                  Text(entry.UVCI!),
                                ]),
                              ];
                            })
                            .expand((i) => i)
                            .toList()
                      ]
                    ],
                  ),
                  actions: [TextButton(onPressed: () => Navigator.pop(context, 'OK'), child: const Text('OK'))],
                );
              });
        }
        break;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('EU Certificate'),
        actions: [
          if (qrFile != null) ...[
            PopupMenuButton<int>(
              onSelected: (item) => handleClick(item),
              itemBuilder: (context) => [
                const PopupMenuItem<int>(value: 0, child: Text('Change Certificate')),
                if (certificateData != null) ...[
                  const PopupMenuItem<int>(value: 1, child: Text('More Info')),
                ]
              ],
            ),
          ]
        ],
      ),
      body: Container(
        margin: const EdgeInsets.symmetric(horizontal: 10),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              if (qrFile != null) ...[
                const Spacer(),
                Image.file(qrFile!),
                const Spacer(),
                if (certificateData != null) ...[
                  Text(certificateData!.Name!.Forename!),
                  Text(certificateData!.Name!.Surname!),
                  Text(certificateData!.DateOfBirth!),
                  const Spacer(),
                ]
              ] else ...[
                ElevatedButton(
                  child: const Text('Pick Certificate Image'),
                  onPressed: _getCertificate,
                ),
              ]
            ],
          ),
        ),
      ),
    );
  }
}
