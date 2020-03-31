package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object PersonalDataTable : LongIdTable("personal_data") {
  var birthday = datetime("birthday")
  var sex = integer("sex")
  val firstName = varchar("first_name", 200)
  val firstLastName = varchar("first_last_name", 200)
  val secondLastName = varchar("second_last_name", 200)
  val mothersName = varchar("mothers_name", 250)
  val rfc = varchar("rfc", 50)
  val curp = varchar("curp", 255)
  val maritalStatus = varchar("marital_status", 64)
  val docIdentificationNumber = varchar("doc_identification_number", 20)
  val placeOfBirth = varchar("place_of_birth", 250)
  val nationality = varchar("nationality", 250)
  val insuranceNumber = varchar("insurance_number", 250)
  val docIssuanceDate = datetime("doc_issuance_date")
  val docIssuer = varchar("doc_issuer", 250)
  val docIssuerState = varchar("doc_issuer_state", 20)
}
