package listeners

import SelenideCustomDriver
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import utils.ScreenshotUtils

class TestListener : AfterEachCallback {

  override fun afterEach(context: ExtensionContext) {
    if (context.executionException.isPresent) {
      ScreenshotUtils.takeScreenshot(context.displayName)
    }
    SelenideCustomDriver.quit()
  }
}