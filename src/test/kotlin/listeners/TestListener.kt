package listeners

import SelenideCustomDriver
import org.apache.logging.log4j.LogManager
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import utils.ScreenshotUtils

class TestListener : AfterEachCallback {
  private val logger = LogManager.getLogger(this.javaClass.name)

  override fun afterEach(context: ExtensionContext?) {
    if (context == null) {
      logger.error("Extension context is null")
    } else if (context.executionException.isPresent) {
      ScreenshotUtils.takeScreenshot(context.displayName)
      ScreenshotUtils.takeScreenshot(context.displayName)
    }
    SelenideCustomDriver.quit()
  }
}