package ui

import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import listeners.TestListener
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(TestListener::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest(protected val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {

  @BeforeAll
  fun configureDriver() {
    SelenideCustomDriver.configureDriver()
  }

  @BeforeEach
  fun authorize() {
    SelenideCustomDriver.authorize()
  }
}