package db.tables.mx_master_moneyman.entities

import db.tables.mx_master_moneyman.UserAccountTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserAccount(id: EntityID<Long>) : Entity<Long>(id) {
  companion object : EntityClass<Long, UserAccount>(UserAccountTable)

  val blocked by UserAccountTable.blocked
  val changePasswordOnLogin by UserAccountTable.changePasswordOnLogin
  val creationDate by UserAccountTable.creationDate
  val creator by UserAccountTable.creator
  val email: String by UserAccountTable.email
}