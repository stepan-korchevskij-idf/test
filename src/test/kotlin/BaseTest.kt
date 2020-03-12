import com.codeborne.selenide.junit5.ScreenShooterExtension
import config.environment.EnvironmentConfigProvider
import config.environment.EnvironmentConfiguration
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import utils.NavigateOperations

@ExtendWith(ScreenShooterExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
  protected lateinit var environmentConfiguration: EnvironmentConfiguration

  @BeforeAll
  fun beforeAll() {
    environmentConfiguration = EnvironmentConfigProvider.getConfiguration()
    NavigateOperations.authorize(environmentConfiguration)
  }

  @AfterEach
  fun quitDriver() {
//    SelenideCustomDriver.quit() //todo удалить метод
  }
}