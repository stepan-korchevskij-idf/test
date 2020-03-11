import config.driver.DriverConfigProvider
import config.driver.DriverConfiguration
import config.environment.EnvironmentConfigProvider
import config.environment.EnvironmentConfiguration
import driver.DefaultDriverFactory
import driver.DriverFactoryManager
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.WebDriver
import utils.NavigateOperations

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  private lateinit var driverFactory: DefaultDriverFactory
  private lateinit var driverConfiguration: DriverConfiguration
  protected lateinit var driver: WebDriver
  protected lateinit var environmentConfiguration: EnvironmentConfiguration

  @BeforeAll
  fun beforeAll() {
    driverConfiguration = DriverConfigProvider.getConfiguration()
    environmentConfiguration = EnvironmentConfigProvider.getConfiguration()
    driverFactory = DriverFactoryManager.getDriverFactory(driverConfiguration)
    driver = driverFactory.getDriver()
    NavigateOperations.authorize(environmentConfiguration, driver)
  }

  @AfterAll
  fun afterAll() {
    driver.quit()
  }
}