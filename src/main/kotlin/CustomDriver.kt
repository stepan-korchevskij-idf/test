import config.driver.DriverConfigProvider
import driver.DriverFactoryManager
import logger.ThreadLogger
import org.apache.logging.log4j.Level
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.util.concurrent.TimeUnit

object CustomDriver {
  private val driverConfiguration = DriverConfigProvider.getConfiguration()
  private val driverThreadLocal = ThreadLocal<WebDriver>()
  val instance: WebDriver
    //сделать private
    get() {
      return driverThreadLocal.get() ?: createInstanceDriver()
    }

  private fun createInstanceDriver(): WebDriver {
    ThreadLogger.getLogger().log(Level.INFO, "Crate driver instance")
    val driver = DriverFactoryManager.getDriverFactory(driverConfiguration).getDriver()
    driverThreadLocal.set(driver)
    return driver
  }

  fun go(url: String) {
    ThreadLogger.getLogger().log(Level.INFO, "Navigate to url - '$url'")
    instance.get(url)
  }

  fun closeDriver() {
    ThreadLogger.getLogger().log(Level.INFO, "Close driver")
    instance.quit()
  }

  fun findElement(locator: By): WebElement {
    return instance.findElement(locator)
  }

  fun findElements(locator: By): MutableList<WebElement> {
    return instance.findElements(locator)
  }

  object Timeouts {
    fun implicitlyWait(timeout: Long) {
      instance.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS)
    }

    fun setDefaultImplicitlyWait() {
      instance.manage().timeouts().implicitlyWait(driverConfiguration.implicitlyDefaultTimeoutSeconds, TimeUnit.SECONDS)
    }
  }
}