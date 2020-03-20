package services.crm

import SelenideCustomDriver
import api.client.data.CookieType
import com.codeborne.selenide.Selenide
import config.environment.EnvironmentConfiguration
import context.DynamicContextHolder
import pages.crm.MainCrmPage

class MxUiOperations(private val envConfig: EnvironmentConfiguration) {
  val mainPage by lazy { MainCrmPage() }

  fun addSessionCookieToBrowser() {
    SelenideCustomDriver.addCookie(
      CookieType.JSESSIONID.text,
      DynamicContextHolder.dynamicContext.sessionCookie[CookieType.JSESSIONID.text]!!
    )
  }

  fun openStartPage() {
    Selenide.open(envConfig.crmStartEndpoint)
  }
}