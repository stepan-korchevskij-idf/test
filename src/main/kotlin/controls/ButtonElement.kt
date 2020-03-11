package controls

import CustomDriver
import logger.ThreadLogger
import org.apache.logging.log4j.Level
import org.openqa.selenium.By

object ButtonElement {
  fun click(loginButton: By) {
    ThreadLogger.getLogger().log(Level.INFO, "Click button - '$loginButton'")
    CustomDriver.findElement(loginButton).click()
  }
}
