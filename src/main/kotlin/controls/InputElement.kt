package controls

import CustomDriver
import logger.ThreadLogger
import org.apache.logging.log4j.Level
import org.openqa.selenium.By

object InputElement {

  fun type(locator: By, text: String) {
    ThreadLogger.getLogger().log(Level.INFO, "Typing text - '$text' in input - '$locator'")
    CustomDriver.findElement(locator).sendKeys(text)
  }
}