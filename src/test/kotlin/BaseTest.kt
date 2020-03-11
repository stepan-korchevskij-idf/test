import config.environment.EnvironmentConfigProvider
import config.environment.EnvironmentConfiguration
import logger.ThreadLogger
import org.apache.logging.log4j.Level
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import utils.NavigateOperations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  protected lateinit var environmentConfiguration: EnvironmentConfiguration

  @BeforeAll
  fun beforeAll() {
    ThreadLogger.getLogger().log(Level.INFO, "Run beforeAll method")
    environmentConfiguration = EnvironmentConfigProvider.getConfiguration()
    NavigateOperations.authorize(environmentConfiguration)
  }

  @AfterAll
  fun afterAll() {
    ThreadLogger.getLogger().log(Level.INFO, "Run afterAll method")
    CustomDriver.closeDriver()
  }
}