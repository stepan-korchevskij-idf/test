import config.environment.EnvironmentConfigProvider
import config.environment.EnvironmentConfiguration
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import utils.NavigateOperations
import utils.takeScreenshot

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  protected lateinit var environmentConfiguration: EnvironmentConfiguration

  @BeforeEach
  fun authorize() {
    environmentConfiguration = EnvironmentConfigProvider.getConfiguration()
    NavigateOperations.authorize(environmentConfiguration)
    takeScreenshot("init")
  }

  @AfterEach
  fun quitDriver() {
    SelenideCustomDriver.quit()
  }
}