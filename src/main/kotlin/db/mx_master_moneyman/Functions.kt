package db.mx_master_moneyman

import db.mx_master_moneyman.tables.UserAccountTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.CustomFunction
import org.jetbrains.exposed.sql.VarCharColumnType

val decode = CustomFunction<String?>(
  "mm_aes_decrypt",
  VarCharColumnType(),
  UserAccountTable.phone
)

fun <T> decrypt(column: Column<T>): CustomFunction<String?> {
  return CustomFunction("mm_aes_decrypt", VarCharColumnType(), column)
}