package utils

import SelenideCustomDriver
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.shooting.ShootingStrategies
import java.io.File
import javax.imageio.ImageIO

//todo как узнать папку конкретного теста
fun takeScreenshot(fileName: String) {
  val pathname = System.getProperty("user.dir") + "\\build\\reports\\tests\\$fileName.png"
  val screenshot = AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
    .takeScreenshot(SelenideCustomDriver.getDriver())
  ImageIO.write(screenshot.image, "PNG", File(pathname))
}