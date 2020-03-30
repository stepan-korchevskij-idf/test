package dataProviders

import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import data.User
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import services.MxDbOperations
import java.util.stream.Stream

class CorrectUsersArgumentsProvider(envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) :
  DefaultArgumentsProvider(envConfig) {

  override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
    return Stream.of(
      Arguments.of(MxDbOperations().getUserForSuccessfullyAuthorization()),
      Arguments.of(User("ta-eqqzghjsuq-0267867945@mail.ru", envConfig.privateAreaDefaultUserPassword!!))
    )
  }
}