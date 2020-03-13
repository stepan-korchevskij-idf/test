import data.User
import org.junit.jupiter.api.Test
import services.MxPrivateAreaOperations

class RemoteRunningTest : BaseTest() {

  init {
    System.setProperty("test.driver.execution", "remote")
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

