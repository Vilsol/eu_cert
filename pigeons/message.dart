// ignore_for_file: non_constant_identifier_names

import 'package:pigeon/pigeon.dart';

class NameData {
  String? Forename;
  String? Surname;
}

class VaccinationEntry {
  String? TargetAgent;
  String? VaccineOrProphylaxis;
  String? VaccineMedicalProduct;
  String? MarketingAuthorizationHolder;
  int? DoseNumber;
  int? TotalDoses;
  String? DateOfVaccination;
  String? CountryOfVaccination;
  String? CertificateIssuer;
  String? UVCI;
}

class TestEntry {
  String? TargetAgent;
  String? TestType;
  String? TestName;
  String? TestManufacturer;
  String? SampleCollected;
  String? TestResult;
  String? TestingCentre;
  String? CountryOfTest;
  String? CertificateIssuer;
  String? UVCI;
}

class RecoveryEntry {
  String? TargetAgent;
  String? FirstPositiveResult;
  String? DateValidFrom;
  String? DateValidUntil;
  String? CountryOfTest;
  String? CertificateIssuer;
  String? UVCI;
}

class CertificatePayload {
  String? Version;
  NameData? Name;
  String? DateOfBirth;
  List<VaccinationEntry?>? Vaccinations;
  List<TestEntry?>? Tests;
  List<RecoveryEntry?>? Recoveries;
}

@HostApi()
abstract class DecoderAPI {
  CertificatePayload decode(Uint8List data);
}
