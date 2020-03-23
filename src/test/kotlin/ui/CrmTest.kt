package ui

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import org.junit.jupiter.api.Test
import services.crm.MxCrmOperations

class CrmTest : BaseTest() {

  @Test
  fun checkAuthorizationAsAdmin() {
    MxCrmOperations().apply {
      openStartPage()
      `$`(mxUiOperations.mainPage.role).shouldHave(text("admin"))
    }
  }
}