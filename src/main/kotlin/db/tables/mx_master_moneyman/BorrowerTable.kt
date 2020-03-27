package db.tables.mx_master_moneyman

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object BorrowerTable : LongIdTable("borrower") {
  val userAccountId = reference("user_account_id", UserAccountTable)
  val personalDataId = reference("personal_data_id", PersonalDataTable)
  val creationDate = datetime("creation_date")
}