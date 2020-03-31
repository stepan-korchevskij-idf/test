package data

import config.environment.EnvironmentConfigurationHolder
import db.mx_master_moneyman.decrypt
import db.mx_master_moneyman.tables.*
import org.jetbrains.exposed.sql.ResultRow
import org.joda.time.LocalDate

data class User(
  var userId: Long? = null,
  var login: String? = null,
  var registrationUuid: String? = null,
  var name: String? = null,
  var firstName: String? = null,
  var firstLastName: String? = null,
  var secondLastName: String? = null,
  var mothersName: String? = null,
  var phone: String? = null,
  var phoneInternationalCode: String? = null,
  var email: String? = null,
  var confirmationCode: String? = null,
  var confirmPolicy: Boolean? = null,
  var sex: String? = null,
  var birthday: LocalDate? = null,
  var maritalStatus: String? = null,
  var docIdentificationNumber: String? = null,
  var placeOfBirth: String? = null,
  var nationality: String? = null,
  var insuranceNumber: String? = null,
  var neighborhood: String? = null,
  var docIssuanceDate: LocalDate? = null,
  var docIssuer: String? = null,
  var docIssuerState: String? = null,
  var curp: String? = null,
  var rfc: String? = null,
  var zipCode: String? = null,
  var state: String? = null,
  var city: String? = null,
  var street: String? = null,
  var houseNumber: String? = null,
  var apartments: String? = null,
  var homePhone: String? = null,
  var housingType: String? = null,
  var streetNumber: String? = null,
  var district: String? = null,
  var municipality: String? = null,
  var issuerNumberOther: String? = null,
  var referencePhoneType: String? = null,
  var referencePhoneName: String? = null,
  var referencePhoneNumber: String? = null,
  var netWorth: String? = null,
  var secondaryMobilePhone: String? = null,
  var education: String? = null,
  var attestedIncome: String? = null,
  var employment: String? = null,
  var company: String? = null,
  var workPhone: String? = null,
  var extensionWorkPhone: String? = null,
  var nextSalaryDate: LocalDate? = null,
  var monthlyExpenses: String? = null,
  var paymentsAmount: String? = null,
  var salaryFrequency: String? = null,
  var industry: String? = null,
  var loanReason: String? = null,
  var leadId: Int? = null,
  var privateAreaPassword: String? = null,
  var partnerId: Int? = null
) {

  constructor(resultRow: ResultRow?) : this() {
    if (resultRow == null) return
    userId = resultRow[UserAccountTable.id].value
    login = resultRow.getOrNull(UserAccountTable.login)
    registrationUuid = resultRow.getOrNull(UserAccountTable.registrationUuid)
    name = resultRow.getOrNull(UserAccountTable.name)
    firstName = resultRow.getOrNull(PersonalDataTable.firstName)
    firstLastName = resultRow.getOrNull(PersonalDataTable.firstLastName)
    secondLastName = resultRow.getOrNull(PersonalDataTable.secondLastName)
    mothersName = resultRow.getOrNull(PersonalDataTable.mothersName)
    phone = resultRow.getOrNull(decrypt(UserAccountTable.phone)) ?: resultRow.getOrNull(UserAccountTable.phone)
//    phoneInternationalCode = resultRow.getOrNull(UserAccountTable)
    email = resultRow.getOrNull(UserAccountTable.email)
    confirmationCode = resultRow.getOrNull(CreditTable.confirmCode)
    confirmPolicy = resultRow.getOrNull(UserAccountTable.confirmPolicy)
    sex = if (resultRow.getOrNull(PersonalDataTable.sex) == 1) "m" else "f"
    birthday = resultRow.getOrNull(PersonalDataTable.birthday)?.toLocalDate()
    maritalStatus = resultRow.getOrNull(PersonalDataTable.maritalStatus)
    docIdentificationNumber = resultRow.getOrNull(PersonalDataTable.docIdentificationNumber)
    placeOfBirth = resultRow.getOrNull(PersonalDataTable.placeOfBirth)
    nationality = resultRow.getOrNull(PersonalDataTable.nationality)
    insuranceNumber = resultRow.getOrNull(PersonalDataTable.insuranceNumber)
//    neighborhood = resultRow.getOrNull(Table)
    docIssuanceDate = resultRow.getOrNull(PersonalDataTable.docIssuanceDate)?.toLocalDate()
    docIssuer = resultRow.getOrNull(PersonalDataTable.docIssuer)
    docIssuerState = resultRow.getOrNull(PersonalDataTable.docIssuerState)
    curp = resultRow.getOrNull(PersonalDataTable.curp)
    rfc = resultRow.getOrNull(PersonalDataTable.rfc)
    zipCode = resultRow.getOrNull(AddressTable.zipCode)
    state = resultRow.getOrNull(AddressTable.state)
    city = resultRow.getOrNull(AddressTable.city)
    street = resultRow.getOrNull(AddressTable.street)
    houseNumber = resultRow.getOrNull(AddressTable.houseNumber)
    apartments = resultRow.getOrNull(AddressTable.apartments)
    homePhone = resultRow.getOrNull(decrypt(AddressTable.homePhone)) ?: resultRow.getOrNull(AddressTable.homePhone)
    housingType = resultRow.getOrNull(AddressTable.housingType)
//    streetNumber = resultRow.getOrNull(Table)
    district = resultRow.getOrNull(AddressTable.district)
    municipality = resultRow.getOrNull(AddressTable.municipality)
//    issuerNumberOther = resultRow.getOrNull(Table)
//    referencePhoneType = resultRow.getOrNull(Table)
//    referencePhoneName = resultRow.getOrNull(Table)
//    referencePhoneNumber = resultRow.getOrNull(Table)
//    netWorth = resultRow.getOrNull(Table)
//    secondaryMobilePhone = resultRow.getOrNull(Table)
    education = resultRow.getOrNull(WorkTable.education)
    attestedIncome = resultRow.getOrNull(WorkTable.attestedIncome).toString()
    employment = resultRow.getOrNull(WorkTable.employment)
    company = resultRow.getOrNull(WorkTable.company)
    workPhone = resultRow.getOrNull(decrypt(WorkTable.workPhone)) ?: resultRow.getOrNull(WorkTable.workPhone)
    extensionWorkPhone = resultRow.getOrNull(WorkTable.workPhoneExtension)
    nextSalaryDate = resultRow.getOrNull(WorkTable.nextSalaryDate)?.toLocalDate()
    monthlyExpenses = resultRow.getOrNull(WorkTable.monthlyExpenses).toString()
//    paymentsAmount = resultRow.getOrNull(WorkTable.paymentsAmount)
//    salaryFrequency = resultRow.getOrNull(WorkTable.salaryFrequency)
    industry = resultRow.getOrNull(WorkTable.industry)
//    loanReason = resultRow.getOrNull(WorkTable.loa)
//    leadId = resultRow.getOrNull(WorkTable.education)
    privateAreaPassword = EnvironmentConfigurationHolder.configuration.privateAreaDefaultUserPassword
//    partnerId: Int? = null
  }
}