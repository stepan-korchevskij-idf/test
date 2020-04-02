package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object AddressTable : LongIdTable("address") {
  val zipCode = text("zip_code")
  val city = text("city")
  val street = text("street")
  val state = text("state")
  val houseNumber = text("house_number")
  val apartments = text("apartments")
  val homePhone = text("home_phone")
  val district = text("district")
  val municipality = text("municipality")
  val housingType = text("housing_type")
}