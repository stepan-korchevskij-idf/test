import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {

  @BeforeAll
  fun configureDriver() {
    SelenideCustomDriver.configureDriver()
  }

  @BeforeEach
  fun authorize() {
    SelenideCustomDriver.authorize()
  }

  @AfterEach
  fun quitDriver() {
    SelenideCustomDriver.quit()
  }
}