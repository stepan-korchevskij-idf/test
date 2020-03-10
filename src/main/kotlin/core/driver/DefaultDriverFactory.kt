package core.driver

import config.driver.DriverConfiguration
import org.openqa.selenium.Dimension
import org.openqa.selenium.Platform
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.util.concurrent.TimeUnit

abstract class DefaultDriverFactory(open var driverConfiguration: DriverConfiguration) : DriverFactory {
  private var driverThreadLocal: ThreadLocal<WebDriver> = ThreadLocal()

  protected abstract fun initDriver(desiredCapabilities: DesiredCapabilities): WebDriver
  protected abstract fun createCapability(generalDesiredCapabilities: DesiredCapabilities): DesiredCapabilities

  override fun getDriver(): WebDriver {
    if (driverThreadLocal.get() == null) {
      val createdDriver = initDriver(createCapability(getGeneralDesiredCapabilities()))
      configureDriver(createdDriver)
      driverThreadLocal.set(createdDriver)
    }
    return driverThreadLocal.get()
  }

  private fun getGeneralDesiredCapabilities(): DesiredCapabilities {
    val desiredCapabilities = DesiredCapabilities()
    desiredCapabilities.platform = Platform.WINDOWS
    return desiredCapabilities
  }

  protected fun setSystemPropertyForInitLocalWebDriver(key: String, value: String) {
    System.setProperty(key, value)
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