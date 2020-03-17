package config.environment

object EnvironmentConfigurationHolder {
  val configuration = EnvironmentConfigProvider.getConfiguration()
}