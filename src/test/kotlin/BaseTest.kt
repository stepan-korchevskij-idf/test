import config.driver.DriverConfigProvider
import config.driver.DriverConfiguration
import config.environment.EnvironmentConfigProvider
import config.environment.EnvironmentConfiguration
import core.driver.DefaultDriverFactory
import core.driver.DriverFactoryManager
import core.utils.NavigateOperations
import core.utils.SystemPropertiesConfigurator
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.WebDriver

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  private lateinit var defaultDriverFactory: DefaultDriverFactory
  private lateinit var driverConfiguration: DriverConfiguration
  protected lateinit var driver: WebDriver
  protected lateinit var environmentConfiguration: EnvironmentConfiguration

  @BeforeAll
  fun beforeAll() {
    driverConfiguration = DriverConfigProvider.getConfiguration()
    environmentConfiguration = EnvironmentConfigProvider.getConfiguration()
    defaultDriverFactory = DriverFactoryManager.getDriverFactory(driverConfiguration)
    driver = defaultDriverFactory.getDriver()
    NavigateOperations.authorize(environmentConfiguration, driver)
  }

  @AfterAll
  fun afterAll() {
    SystemPropertiesConfigurator.clearAll()
    driver.quit()
  }
}