package ui.driver.selenium

import config.driver.DriverConfiguration
import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile

class FirefoxDriverFactory(driverConfiguration: DriverConfiguration) : DefaultDriverFactory(driverConfiguration) {

  private val systemPropertyForInitDriver by lazy {
    Pair("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe")
  }

  override fun createCapability(): FirefoxOptions {
    val firefoxOptions = FirefoxOptions()
    firefoxOptions.merge(getGeneralDesiredCapabilities())
    firefoxOptions.profile = getFirefoxProfile()
    return firefoxOptions
  }

  override fun createDriver(capabilities: Capabilities): WebDriver {
    initLocalDriverLocation(systemPropertyForInitDriver.first, systemPropertyForInitDriver.second)
    return FirefoxDriver(capabilities as FirefoxOptions)
  }

  private fun getFirefoxProfile(): FirefoxProfile {
    val profile = FirefoxProfile()
    with(profile) {
      setPreference("capability.policy.default.HTMLDocument.readyState", "allAccess")
      setPreference("capability.policy.default.HTMLDocument.compatMode", "allAccess")
      setPreference("capability.policy.default.Document.compatMode", "allAccess")
      setPreference("capability.policy.default.Location.href", "allAccess")
      setPreference("capability.policy.default.Window.pageXOffset", "allAccess")
      setPreference("capability.policy.default.Window.pageYOffset", "allAccess")
      setPreference("capability.policy.default.Window.frameElement", "allAccess")
      setPreference("capability.policy.default.Window.frameElement.get", "allAccess")
      setPreference("capability.policy.default.Window.QueryInterface", "allAccess")
      setPreference("capability.policy.default.Window.mozInnerScreenY", "allAccess")
      setPreference("capability.policy.default.Window.mozInnerScreenX", "allAccess")
    }
    return profile
  }
}