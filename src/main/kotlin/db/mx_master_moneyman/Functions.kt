package db.mx_master_moneyman

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.CustomFunction
import org.jetbrains.exposed.sql.VarCharColumnType

fun <T> decrypt(column: Column<T>): CustomFunction<String?> {
  return CustomFunction("mm_aes_decrypt", VarCharColumnType(), column)
}