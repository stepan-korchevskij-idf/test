package config.environment

object EnvironmentConfigurationHolder {
  val environmentConfiguration = EnvironmentConfigProvider.getConfiguration()
}