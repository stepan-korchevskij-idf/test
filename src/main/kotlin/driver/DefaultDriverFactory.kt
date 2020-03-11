package driver

import config.driver.DriverConfiguration
import org.openqa.selenium.Capabilities
import org.openqa.selenium.Dimension
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.concurrent.TimeUnit

abstract class DefaultDriverFactory(open var driverConfiguration: DriverConfiguration) : DriverFactory {
  private var driverThreadLocal: ThreadLocal<WebDriver> = ThreadLocal()

  protected abstract fun createDriver(capabilities: Capabilities): WebDriver
  protected abstract fun createCapability(): Capabilities

  override fun getDriver(): WebDriver {
    return driverThreadLocal.get() ?: initDriver()
  }

  protected fun initLocalDriverLocation(key: String, value: String) {
    System.setProperty(key, value)
  }

  private fun initDriver(): WebDriver {
    val createdDriver = createDriver(createCapability())
    configureDriver(createdDriver)
    driverThreadLocal.set(createdDriver)
    return createdDriver
  }

  protected fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
    return desiredCapabilities
  }

  private fun configureDriver(driver: WebDriver) {
    driver.manage().window().size = Dimension(driverConfiguration.windowWidth, driverConfiguration.windowHeight)
    setTimeouts(driver)
  }

  private fun setTimeouts(driver: WebDriver) {
    driver.manage().timeouts().apply {
      implicitlyWait(driverConfiguration.implicitlyDefaultTimeoutSeconds, TimeUnit.SECONDS)
      pageLoadTimeout(driverConfiguration.pageLoadedDefaultTimeoutSeconds, TimeUnit.SECONDS)
      setScriptTimeout(driverConfiguration.scriptDefaultTimeoutSeconds, TimeUnit.SECONDS)
    }
  }
}