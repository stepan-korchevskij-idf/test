package driver

import config.driver.DriverConfiguration
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

class ChromeDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {

  private val systemPropertyForInitDriver by lazy {
    Pair(
      "webdriver.chrome.driver",
      "src/test/resources/drivers/chromedriver.exe"
    )
  }

  public override fun createCapability(): Capabilities {
    val chromeCapabilities = ChromeOptions()
    chromeCapabilities.merge(getGeneralDesiredCapabilities())
    return chromeCapabilities
  }

  override fun createDriver(capabilities: Capabilities): WebDriver {
    initLocalDriverLocation(systemPropertyForInitDriver.first, systemPropertyForInitDriver.second)
    return ChromeDriver(capabilities as ChromeOptions)
  }
}