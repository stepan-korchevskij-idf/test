package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object BorrowerTable : LongIdTable("borrower") {
  val personalDataId = reference("personal_data_id", PersonalDataTable)
  val userAccountId = reference("user_account_id", UserAccountTable)
  val workId = reference("work_id", WorkTable)
  val addressId = reference("address_id", AddressTable)
  val creationDate = datetime("creation_date")
}