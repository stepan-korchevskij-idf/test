package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object CreditTable : LongIdTable("credit") {
  val confirmCode = PersonalDataTable.varchar("confirm_code", 255)
}