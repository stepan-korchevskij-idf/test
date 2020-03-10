package core.driver

import config.driver.DriverConfiguration
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class FirefoxDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {

  private val systemPropertyForInitDriver by lazy {
    Pair(
      "webdriver.gecko.driver",
      "src/test/resources/drivers/geckodriver.exe"
    )
  }

  override fun createCapability(generalDesiredCapabilities: DesiredCapabilities): DesiredCapabilities {
    val firefoxCapability = DesiredCapabilities.firefox()
    generalDesiredCapabilities.merge(firefoxCapability)
    return generalDesiredCapabilities
  }

  override fun initDriver(desiredCapabilities: DesiredCapabilities): WebDriver {
    return when (driverConfiguration.driverExecutionType) {
      DriverExecutionType.REMOTE -> RemoteWebDriver(URL(driverConfiguration.hubUrl), desiredCapabilities)
      DriverExecutionType.LOCAL -> {
        setSystemPropertyForInitLocalWebDriver(systemPropertyForInitDriver.first, systemPropertyForInitDriver.second)
        FirefoxDriver(desiredCapabilities)
      }
    }
  }
}