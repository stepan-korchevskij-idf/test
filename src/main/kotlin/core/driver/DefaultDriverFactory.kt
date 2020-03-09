package core.driver

import config.driver.DriverConfiguration
import org.openqa.selenium.WebDriver
import java.util.concurrent.TimeUnit

abstract class DefaultDriverFactory(open var driverConfiguration: DriverConfiguration) : DriverFactory {
  protected var driverThreadLocal: ThreadLocal<WebDriver> = ThreadLocal()
  protected abstract fun createDriver()

  override fun getDriver(): WebDriver {
    if (driverThreadLocal.get() == null) {
      createDriver()
      setTimeouts()
    }
    return driverThreadLocal.get()
  }

  private fun setTimeouts() {
    driverThreadLocal.get().manage().timeouts().apply {
      implicitlyWait(driverConfiguration.implicitlyDefaultTimeoutSeconds, TimeUnit.SECONDS)
      pageLoadTimeout(driverConfiguration.pageLoadedDefaultTimeoutSeconds, TimeUnit.SECONDS)
      setScriptTimeout(driverConfiguration.scriptDefaultTimeoutSeconds, TimeUnit.SECONDS)
    }
  }
}