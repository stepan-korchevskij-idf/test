package ui

import data.User
import org.junit.jupiter.api.Test
import services.priveteArea.MxPrivateAreaOperations

class HeadlessTest : BaseTest() {

  @Test
  fun checkLogin() {
    val user = User("ta-eqqzghjsuq-0267867945@mail.ru", envConfig.privateAreaDefaultUserPassword!!)
    MxPrivateAreaOperations().apply {
      openStartPage()
      loginPage.checkOpened()
      loginPage.login(user)
      loanDetailsPage.checkOpened()
    }
  }
}

