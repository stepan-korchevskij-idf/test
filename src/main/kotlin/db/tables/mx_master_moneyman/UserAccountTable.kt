package db.tables.mx_master_moneyman

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime

object UserAccountTable : LongIdTable("user_account") {

  val blocked: Column<ByteArray> = binary("blocked", 1)
  val changePasswordOnLogin: Column<ByteArray> = binary("change_password_on_login", 1)
  val creationDate: Column<DateTime> = datetime("creation_date")
  val creator: Column<String> = varchar("creator", 32)
  val email: Column<String> = varchar("email", 254)
  val password: Column<String> = varchar("password", 64)
}