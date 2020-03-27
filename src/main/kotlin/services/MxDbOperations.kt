package services

import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import data.User
import db.DataSourceFactory
import db.tables.mx_master_moneyman.UserAccountTable
import db.tables.mx_master_moneyman.entities.UserAccount
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class MxDbOperations(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  val db: Database = Database.connect(DataSourceFactory(envConfig).getMysqlDataSource())

  fun getUserForSuccessfullyAuthorization(): User {
    val userAccount = transaction {
      UserAccount.find { UserAccountTable.email.like("%@%") }.first()
    }
    return User(userAccount.email, envConfig.privateAreaDefaultUserPassword!!)
  }
}