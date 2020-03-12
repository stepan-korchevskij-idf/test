import config.environment.EnvironmentConfigProvider
import config.environment.EnvironmentConfiguration
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import utils.NavigateOperations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  protected lateinit var environmentConfiguration: EnvironmentConfiguration

  @BeforeAll
  fun beforeAll() {
    environmentConfiguration = EnvironmentConfigProvider.getConfiguration()
    NavigateOperations.authorize(environmentConfiguration)
  }

  @AfterAll
  fun afterAll() {
    CustomDriver.quit()
  }
}