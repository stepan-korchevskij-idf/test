package services

import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import data.User
import db.DataSourceFactory
import db.SqlLogger
import db.mx_master_moneyman.decrypt
import db.mx_master_moneyman.entities.UserAccount
import db.mx_master_moneyman.tables.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class MxDbOperations(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  val db: Database = Database.connect(DataSourceFactory(envConfig).getMysqlDataSource())

  fun getUserForSuccessfullyAuthorization(): User {
    val userAccount = transaction {
      addLogger(SqlLogger)
      UserAccount.find { UserAccountTable.email.like("%@%") }.first()
    }
    return User(email = userAccount.email, privateAreaPassword = envConfig.privateAreaDefaultUserPassword!!)
  }

  fun getUserById(id: Long): User? {
    return transaction {
      addLogger(SqlLogger)
      BorrowerTable.innerJoin(PersonalDataTable)
        .innerJoin(UserAccountTable)
        .innerJoin(WorkTable)
        .innerJoin(AddressTable)
        .slice(
          PersonalDataTable.columns + UserAccountTable.columns + WorkTable.columns + AddressTable.columns +
              decrypt(UserAccountTable.phone) + decrypt(WorkTable.workPhone) + decrypt(AddressTable.homePhone)
        )
        .select { UserAccountTable.id eq id }
        .firstOrNull()
        ?.let { User(it) }
    }
  }
}