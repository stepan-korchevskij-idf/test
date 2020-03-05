package core

import core.utils.loadProperty
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  protected lateinit var driver: WebDriver
  protected lateinit var configs: Properties
  private val systemProperties: MutableList<Pair<String, String>> = mutableListOf()

  @BeforeAll
  fun beforeAll() {
    configs = loadProperty("configurationEnvironment.yaml")
    val host = configs.getProperty("host")
    val login = configs.getProperty("user")
    val password = configs.getProperty("pass")
    systemProperties.add(
      Pair(
        "webdriver.chrome.driver",
        "C:\\Users\\stepan.korchevskij\\browserDriwers\\chromedriver.exe"
      )
    )
    systemProperties.forEach { pair -> System.setProperty(pair.first, pair.second) }
    driver = ChromeDriver();
    driver.get("https://$login:$password@$host")
  }

  @AfterAll
  fun afterAll() {
    configs.clear()
    driver.close()
    systemProperties.forEach { pair -> System.clearProperty(pair.first) }
  }
}