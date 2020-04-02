package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable

object CreditTable : LongIdTable("credit") {
  val confirmCode = text("confirm_code")
}