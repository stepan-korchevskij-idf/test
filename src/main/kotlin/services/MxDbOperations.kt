package services

import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import db.tables.mx_master_moneyman.DataSourceFactory
import db.tables.mx_master_moneyman.UserAccountTable
import db.tables.mx_master_moneyman.entities.UserAccount
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class MxDbOperations(envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  val db: Database = Database.connect(DataSourceFactory(envConfig).getMySqlDataSource())

  fun getEmailRandomUser(): String {
    return transaction {
      UserAccount.find { UserAccountTable.email.isNotNull() }.first().email
    }
  }
}