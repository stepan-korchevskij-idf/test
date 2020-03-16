package driver

import com.codeborne.selenide.Configuration
import config.driver.DriverConfiguration
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.remote.DesiredCapabilities

class SelenideFirefoxDriverFactory(driverConfiguration: DriverConfiguration) :
  SelenideDefaultDriverFactory(driverConfiguration) {

  override fun createCapability(): DesiredCapabilities {
    Configuration.browserVersion = driverConfiguration.firefoxVersion
    return getGeneralDesiredCapabilities().apply {
      val firefoxOptions = FirefoxOptions()
      firefoxOptions.profile = getFirefoxProfile()
      merge(firefoxOptions)
    }
  }

  override fun createDriver(desiredCapabilities: DesiredCapabilities) {
    Configuration.browserCapabilities = desiredCapabilities
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