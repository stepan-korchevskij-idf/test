package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object PersonalDataTable : LongIdTable("personal_data") {
  var birthday = datetime("birthday")
  var sex = integer("sex")
  val firstName = text("first_name")
  val firstLastName = text("first_last_name")
  val secondLastName = text("second_last_name")
  val mothersName = text("mothers_name")
  val rfc = text("rfc")
  val curp = text("curp")
  val maritalStatus = text("marital_status")
  val docIdentificationNumber = text("doc_identification_number")
  val placeOfBirth = text("place_of_birth")
  val nationality = text("nationality")
  val insuranceNumber = text("insurance_number")
  val docIssuanceDate = datetime("doc_issuance_date")
  val docIssuer = text("doc_issuer")
  val docIssuerState = text("doc_issuer_state")
}
