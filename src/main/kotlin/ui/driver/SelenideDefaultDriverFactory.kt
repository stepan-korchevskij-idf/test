package ui.driver

import com.codeborne.selenide.Configuration
import config.driver.DriverConfiguration
import config.environment.EnvironmentConfigurationHolder
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

abstract class SelenideDefaultDriverFactory(open var driverConfiguration: DriverConfiguration) :
  SelenideDriverFactory {

  protected abstract fun createDriver(desiredCapabilities: DesiredCapabilities = createCapability())
  abstract fun createCapability(): DesiredCapabilities

  override fun getDriver() {
    configureDriver()
    createDriver()
  }

  protected fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }

  private fun configureDriver() {
    Configuration.browserSize = "${driverConfiguration.windowWidth}x${driverConfiguration.windowHeight}"
    Configuration.timeout = driverConfiguration.timeoutSeconds * 1000
    Configuration.browser = driverConfiguration.browserType.name.toLowerCase()
    Configuration.headless = driverConfiguration.headless
    Configuration.baseUrl = EnvironmentConfigurationHolder.configuration.getBaseUrl()
  }
}