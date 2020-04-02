package db.mx_master_moneyman.tables

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.jodatime.datetime

object WorkTable : LongIdTable("work") {
  val education = text("education")
  val attestedIncome = double("attested_income")
  val employment = text("employment")
  val company = text("company")
  val monthlyExpenses = double("monthly_expenses")
  val workPhone = text("work_phone")
  val paymentFrequency = text("payment_frequency")
  val workPhoneExtension = text("work_phone_extension")
  val industry = text("industry")
  val nextSalaryDate = datetime("next_salary_date")
}