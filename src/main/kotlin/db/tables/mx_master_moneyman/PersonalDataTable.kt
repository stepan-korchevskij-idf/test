package db.tables.mx_master_moneyman

import org.jetbrains.exposed.dao.id.LongIdTable

object PersonalDataTable : LongIdTable("personal_data") {
  val firstName = varchar("first_name", 200)
  val firstLastName = varchar("first_last_name", 200)
  val secondLastName = varchar("second_last_name", 200)
  val curp = varchar("curp", 255)
}
