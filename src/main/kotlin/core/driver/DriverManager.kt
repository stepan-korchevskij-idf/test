package core.driver

import config.DriverConfiguration
import org.openqa.selenium.WebDriver
import java.util.concurrent.TimeUnit

abstract class DriverManager {
  protected var driverThreadLocal: ThreadLocal<WebDriver> = ThreadLocal()
  protected abstract fun createDriver()

  fun getDriver(): WebDriver {
    if (driverThreadLocal.get() == null) {
      createDriver()
      setTimeouts()
    }
    return driverThreadLocal.get()
  }

  fun quitDriver() {
    driverThreadLocal.get()?.quit()
  }

  private fun setTimeouts() {
    val timeouts = driverThreadLocal.get().manage().timeouts()
    timeouts.implicitlyWait(DriverConfiguration.implicitlyDefaultTimeoutSeconds, TimeUnit.SECONDS)
    timeouts.pageLoadTimeout(DriverConfiguration.pageLoadedDefaultTimeoutSeconds, TimeUnit.SECONDS)
    timeouts.setScriptTimeout(DriverConfiguration.scriptDefaultTimeoutSeconds, TimeUnit.SECONDS)
  }
}