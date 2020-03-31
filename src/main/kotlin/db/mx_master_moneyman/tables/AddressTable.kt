package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object AddressTable : LongIdTable("address") {
  val zipCode = varchar("zip_code", 250)
  val city = varchar("city", 250)
  val street = varchar("street", 250)
  val state = varchar("state", 250)
  val houseNumber = varchar("house_number", 250)
  val apartments = varchar("apartments", 250)
  val homePhone = varchar("home_phone", 250)
  val district = varchar("district", 250)
  val municipality = varchar("municipality", 250)
  val housingType = varchar("housing_type", 250)
}