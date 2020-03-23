package services

import api.client.data.CookieType
import com.codeborne.selenide.Selenide
import config.environment.EnvironmentConfiguration
import context.DynamicContextHolder
import ui.SelenideCustomDriver
import ui.pages.crm.MainCrmPage

class MxUiOperations(private val envConfig: EnvironmentConfiguration) {
  val mainPage by lazy { MainCrmPage() }

  fun addSessionCookieToBrowser() {
    SelenideCustomDriver.apply {
      deleteCookie(CookieType.JSESSIONID.text)
      addCookie(
        CookieType.JSESSIONID.text,
        DynamicContextHolder.dynamicContext.sessionCookie[CookieType.JSESSIONID.text]!!
      )
    }
  }

  fun openCrmStartPage() {
    Selenide.open(envConfig.crmStartEndpoint)
  }
}