package core.driver

import config.driver.DriverConfiguration
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

class ChromeDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {

  private val systemPropertyForInitDriver by lazy {
    Pair(
      "webdriver.chrome.driver",
      "src/test/resources/drivers/chromedriver.exe"
    )
  }

  override fun createCapability(generalDesiredCapabilities: DesiredCapabilities): DesiredCapabilities {
    val chromeCapabilities = DesiredCapabilities.chrome()
    //add here exclusive capabilities
    chromeCapabilities.merge(generalDesiredCapabilities)
    return chromeCapabilities
  }

  override fun initDriver(desiredCapabilities: DesiredCapabilities): WebDriver {
    return when (driverConfiguration.driverExecutionType) {
      DriverExecutionType.REMOTE -> RemoteWebDriver(URL(driverConfiguration.hubUrl), desiredCapabilities)
      DriverExecutionType.LOCAL -> {
        setSystemPropertyForInitLocalWebDriver(systemPropertyForInitDriver.first, systemPropertyForInitDriver.second)
        ChromeDriver(desiredCapabilities)
      }
    }
  }
}