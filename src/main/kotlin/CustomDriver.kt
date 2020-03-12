import config.driver.DriverConfigProvider
import driver.DriverFactoryManager
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.util.concurrent.TimeUnit

object CustomDriver {
  private val driverConfiguration = DriverConfigProvider.getConfiguration()
  private val driverThreadLocal = ThreadLocal<WebDriver>()
  private val logger = LogManager.getLogger(this.javaClass.name)

  fun getDriver(): WebDriver {
    return driverThreadLocal.get() ?: createInstanceDriver()
  }

  private fun createInstanceDriver(): WebDriver {
    logger.log(Level.INFO, "Creating driver instance")
    val driver = DriverFactoryManager.getDriverFactory(driverConfiguration).getDriver()
    driverThreadLocal.set(driver)
    return driver
  }

  fun get(url: String) {
    logger.log(Level.INFO, "Navigating to url - '$url'")
    getDriver().get(url)
  }

  fun quit() {
    logger.log(Level.INFO, "Quits this driver")
    getDriver().quit()
    driverThreadLocal.remove()
  }

  fun findElement(locator: By): WebElement {
    return getDriver().findElement(locator)
  }

  fun findElements(locator: By): MutableList<WebElement> {
    return getDriver().findElements(locator)
  }

  object Timeouts {
    fun implicitlyWait(timeout: Long) {
      getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS)
    }

    fun setDefaultImplicitlyWait() {
      getDriver().manage().timeouts()
        .implicitlyWait(driverConfiguration.implicitlyDefaultTimeoutSeconds, TimeUnit.SECONDS)
    }
  }
}