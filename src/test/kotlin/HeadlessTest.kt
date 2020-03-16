import data.User
import listeners.TestListener
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import services.MxPrivateAreaOperations

@ExtendWith(TestListener::class)
class HeadlessTest {

  @BeforeAll
  fun configureTest() {
    System.setProperty("test.driver.headless", "true")
    SelenideCustomDriver.configureDriver()
  }

  @BeforeEach
  fun authorize() {
    SelenideCustomDriver.authorize()
  }

  @Test
  fun checkLogin() {
    val user = User(login = "ta-eqqzghjsuq-0267867945@mail.ru", password = "11111111")
    MxPrivateAreaOperations().apply {
      openStartPage()
      loginPage.checkOpened()
      loginPage.login(user)
      loanDetailsPage.checkOpened()
    }
  }
}

