package waiters

import CustomDriver
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.concurrent.TimeUnit

object Waiter {
  private const val DEFAULT_IMPLICITLY_TIMEOUT: Long = 10

  fun waitInvisibilityElement(locator: By, timeout: Long) {
    CustomDriver.Timeouts.implicitlyWait(0)
    WebDriverWait(CustomDriver.instance, timeout)
      .until() {
        try {
          !it.findElement(locator).isDisplayed
        } catch (ex: NoSuchElementException) {
          true
        } catch (ex: StaleElementReferenceException) {
          false
        }
      }
    CustomDriver.Timeouts.setDefaultImplicitlyWait()
  }
}