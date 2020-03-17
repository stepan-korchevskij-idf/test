package ui

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import services.MxCrmOperations

class CrmTest : BaseTest() {

  @Test
  fun checkAuthorizationAsAdmin() {
    MxCrmOperations().apply {
      openStartPage()
      Assertions.assertEquals("admin", mainPage.getRole())
    }
  }
}