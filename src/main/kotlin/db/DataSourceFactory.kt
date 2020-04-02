package db

import com.mysql.cj.jdbc.MysqlDataSource
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class DataSourceFactory(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  private val logger: Logger = LogManager.getLogger(this.javaClass.simpleName)

  fun getMysqlDataSource(schemaDb: SchemaDb): MysqlDataSource {
    logger.info("Create mysql data source")
    return MysqlDataSource().apply {
      setURL("${envConfig.dbSqlUrl}/${schemaDb.schemaName}")
      user = "${envConfig.dbSqlUser}"
      password = "${envConfig.dbSqlPass}"
    }
  }
}