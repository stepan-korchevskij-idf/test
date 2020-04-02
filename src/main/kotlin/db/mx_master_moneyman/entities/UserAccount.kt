package db.mx_master_moneyman.entities

import db.mx_master_moneyman.tables.UserAccountTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserAccount(id: EntityID<Long>) : Entity<Long>(id) {
  companion object : EntityClass<Long, UserAccount>(UserAccountTable)

  val login: String by UserAccountTable.login
  val email: String by UserAccountTable.email
}