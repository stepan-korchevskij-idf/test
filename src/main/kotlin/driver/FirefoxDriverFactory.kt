package driver

import config.driver.DriverConfiguration
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.remote.DesiredCapabilities

class FirefoxDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {

  private val systemPropertyForInitDriver by lazy {
    Pair(
      "webdriver.gecko.driver",
      "src/test/resources/drivers/geckodriver.exe"
    )
  }

  public override fun createCapability(): DesiredCapabilities {
    val firefoxCapability = DesiredCapabilities.firefox()
    firefoxCapability.merge(getGeneralDesiredCapabilities())
    return firefoxCapability
  }

  override fun createDriver(capabilities: Capabilities): WebDriver {
    initLocalDriverLocation(systemPropertyForInitDriver.first, systemPropertyForInitDriver.second)
    return FirefoxDriver(capabilities as FirefoxOptions)
  }
}