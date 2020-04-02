package db

import config.environment.EnvironmentConfigurationHolder

enum class SchemaDb(val schemaName: String?) {
  MX_MASTER_MONEYMAN(EnvironmentConfigurationHolder.configuration.dbSqlBorrowerSchema)
}