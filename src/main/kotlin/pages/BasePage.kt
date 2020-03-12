package pages

import CustomDriver
import org.openqa.selenium.By

abstract class BasePage() {
  abstract var identifierPage: By
  abstract var namePage: String

  fun checkOpened() {
    val foundedIdentifiers = CustomDriver.findElements(identifierPage)
    assert(foundedIdentifiers.size > 0 && foundedIdentifiers[0].isDisplayed) { "$namePage page does not opened." }
  }
}