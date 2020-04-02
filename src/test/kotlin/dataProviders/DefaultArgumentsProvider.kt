package dataProviders

import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import org.junit.jupiter.params.provider.ArgumentsProvider

abstract class DefaultArgumentsProvider(
  protected val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration
) : ArgumentsProvider