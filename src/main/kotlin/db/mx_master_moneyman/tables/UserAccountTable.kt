package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object UserAccountTable : LongIdTable("user_account") {
  val name: Column<String> = varchar("name", 602)
  val registrationUuid: Column<String> = varchar("registration_uuid", 128)
  val email: Column<String> = varchar("email", 254)
  val login: Column<String> = varchar("login", 128)
  val password: Column<String> = varchar("password", 64)
  val phone = varchar("phone", 64)
  val confirmPolicy = bool("confirm_policy")
}