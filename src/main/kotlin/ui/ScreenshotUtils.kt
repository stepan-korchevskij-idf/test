package ui

import org.apache.logging.log4j.LogManager
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.shooting.ShootingStrategies
import java.io.File
import javax.imageio.ImageIO

object ScreenshotUtils {
  private val logger = LogManager.getLogger()

  //todo если в pathname попадают пробелы - получаем в консоль java.io.FileNotFoundException, но catch-ем
  // (и в дебаге) ловим NPE.
  fun takeScreenshot(fileName: String) {
    val pathname = generatePathname(fileName)
    logger.info("Attaching screenshot - '${pathname}'")
    val screenshot = AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
      .takeScreenshot(SelenideCustomDriver.getDriver())
    try {
      ImageIO.write(screenshot.image, "PNG", File(pathname))
    } catch (e: java.io.FileNotFoundException) {
      logger.error("java.io.FileNotFoundException -- " + e.message)
    } catch (e: java.lang.Exception) {
      logger.error("java Exception - " + e.message)
    } catch (e: kotlin.Exception) {
      logger.error("kotlin Exception - " + e.message)
    }
  }

  private fun generatePathname(fileName: String): String {
    return System.getProperty("user.dir") + "\\build\\reports\\tests\\${fileName}_${System.nanoTime()}.png"
  }
}