package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object UserAccountTable : LongIdTable("user_account") {
  val name: Column<String> = text("name")
  val registrationUuid: Column<String> = text("registration_uuid")
  val email: Column<String> = text("email")
  val login: Column<String> = text("login")
  val password: Column<String> = text("password")
  val phone = text("phone")
  val confirmPolicy = bool("confirm_policy")
}