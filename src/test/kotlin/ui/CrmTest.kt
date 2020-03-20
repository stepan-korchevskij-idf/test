package ui

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import services.crm.MxCrmOperations

class CrmTest : BaseTest() {

  @Test
  fun checkAuthorizationAsAdmin() {
    MxCrmOperations().apply {
      openStartPage()
      Assertions.assertEquals("admin", mxUiOperations.mainPage.getRole())
    }
  }
}