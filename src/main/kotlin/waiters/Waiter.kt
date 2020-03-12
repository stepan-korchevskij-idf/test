package waiters

import CustomDriver
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.support.ui.WebDriverWait

object Waiter {
  private const val WAIT_INVISIBILITY_ELEMENT: Long = 10

  fun waitInvisibilityElement(locator: By) {
    CustomDriver.Timeouts.implicitlyWait(0)
    WebDriverWait(CustomDriver.getDriver(), WAIT_INVISIBILITY_ELEMENT)
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