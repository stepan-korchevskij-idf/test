package services

import SelenideCustomDriver
import api.client.CustomClient
import api.client.data.CookieType
import api.generator.CrmRequestGenerator
import com.codeborne.selenide.Selenide
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import context.DynamicContextHolder
import pages.crm.MainCrmPage

class MxCrmOperations(private val envConfig: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration) {
  val mainPage by lazy { MainCrmPage() }

  fun openStartPage() {
    createSessionCookie()
    addSessionCookie()
    Selenide.open(envConfig.crmStartEndpoint)
  }

  private fun addSessionCookie() {
    SelenideCustomDriver.addCookie(
      CookieType.JSESSIONID.text, DynamicContextHolder.dynamicContext.cookie[CookieType.JSESSIONID.text]!!
    )
  }

  private fun createSessionCookie() {
    val crmResponse = CustomClient.sendRequest(CrmRequestGenerator(envConfig).getAuthorizeCrmRequest())
    DynamicContextHolder.dynamicContext.addCookie(crmResponse)
  }
}