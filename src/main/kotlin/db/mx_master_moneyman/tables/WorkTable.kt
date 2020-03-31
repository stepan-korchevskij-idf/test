package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object WorkTable : LongIdTable("work") {
  val education = varchar("education", 250)
  val attestedIncome = double("attested_income")
  val employment = varchar("employment", 250)
  val company = varchar("company", 128)
  val monthlyExpenses = double("monthly_expenses")
  val workPhone = varchar("work_phone", 255)
  val paymentFrequency = varchar("payment_frequency", 40)
  val workPhoneExtension = varchar("work_phone_extension", 40)
  val industry = varchar("industry", 64)
  val nextSalaryDate = datetime("next_salary_date")
}