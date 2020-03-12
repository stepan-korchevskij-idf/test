package driver

import com.codeborne.selenide.Configuration
import config.driver.DriverConfiguration
import driver.selenium.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

abstract class SelenideDefaultDriverFactory(open var driverConfiguration: DriverConfiguration) :
  DriverFactory {

  protected abstract fun createDriver(desiredCapabilities: DesiredCapabilities = createCapability()): WebDriver
  abstract fun createCapability(): DesiredCapabilities

  override fun getDriver(): WebDriver {
    configureDriver()
    return createDriver()
  }

  protected fun initLocalDriverLocation(key: String, value: String) {
    System.setProperty(key, value)
  }

  protected fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }

  private fun configureDriver() {
    Configuration.browserSize = "${driverConfiguration.windowWidth}x${driverConfiguration.windowHeight}"
    Configuration.timeout = 6000//todo из пропертей
  }
}