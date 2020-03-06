package core

import config.DriverConfiguration
import config.EnvironmentConfiguration
import config.YAMLParser
import core.driver.DriverManager
import core.driver.DriverManagerFactory
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.WebDriver

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  protected lateinit var driver: WebDriver
  private lateinit var driverManager: DriverManager
  protected lateinit var environmentConfiguration: EnvironmentConfiguration

  @BeforeAll
  fun beforeAll() {
    environmentConfiguration =
      YAMLParser.parseDto("src/test/resources/environmentConfiguration.yaml", EnvironmentConfiguration::class)
    driverManager = DriverManagerFactory.getManager(DriverConfiguration.driverType)
    driver = driverManager.getDriver()
    val baseUrl =
      "https://${environmentConfiguration.user}:${environmentConfiguration.pass}@${environmentConfiguration.host}"
    driver.get(baseUrl)
  }

  @AfterAll
  fun afterAll() {
    driverManager.quitDriver()
  }
}