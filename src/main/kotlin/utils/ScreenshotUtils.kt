package utils

import SelenideCustomDriver
import org.apache.logging.log4j.LogManager
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.shooting.ShootingStrategies
import java.io.File
import javax.imageio.ImageIO

object ScreenshotUtils {
  private val logger = LogManager.getLogger()

  fun takeScreenshot(fileName: String) {
    val pathname = generatePathname(fileName)
    logger.info("Attaching screenshot - '${pathname}'")
    val screenshot = AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
      .takeScreenshot(SelenideCustomDriver.getDriver())
    ImageIO.write(screenshot.image, "PNG", File(pathname))
  }

  private fun generatePathname(fileName: String): String {
    return System.getProperty("user.dir") + "\\build\\reports\\tests\\${fileName}_${System.nanoTime()}.png"
  }
}