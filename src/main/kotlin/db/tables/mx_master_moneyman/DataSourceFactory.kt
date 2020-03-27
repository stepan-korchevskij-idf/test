package db.tables.mx_master_moneyman

import com.mysql.cj.jdbc.MysqlDataSource
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder

class DataSourceFactory(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  fun getMySqlDataSource(): MysqlDataSource {
    return MysqlDataSource().apply {
      setURL("${envConfig.dbSqlUrl}/${envConfig.dbSqlBorrowerSchema}")
      user = "${envConfig.dbSqlUser}"
      password = "${envConfig.dbSqlPass}"
    }
  }
}