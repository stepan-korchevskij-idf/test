package services

import SelenideCustomDriver
import api.generator.CrmBodyGenerator
import com.codeborne.selenide.Selenide
import config.environment.EnvironmentConfiguration
import config.environment.EnvironmentConfigurationHolder
import okhttp3.*
import org.apache.logging.log4j.LogManager
import org.openqa.selenium.Cookie
import pages.crm.MainCrmPage
import utils.JsonParser
import java.net.HttpCookie

class MxCrmOperations(
  private val environmentConfiguration: EnvironmentConfiguration = EnvironmentConfigurationHolder.configuration
) {
  private val logger = LogManager.getLogger(this.javaClass.simpleName)
  val mainPage by lazy { MainCrmPage() }

  fun openStartPage() {
    val request: Request = getRequest()
    val call: Call = getClient().newCall(request)
    val response: Response = call.execute()
    val crmUser = JsonParser.fromBodyResponse(response, CrmUser::class.java)
    crmUser.error?.let { logger.error(it) }
    val cookiesData = response.headers("Set-Cookie")
    val nameSessionCookie = "JSESSIONID"
    val httpCookie: HttpCookie = HttpCookie.parse(cookiesData.filter { s -> s.startsWith(nameSessionCookie) }[0])[0]
    val seleniumCookie = Cookie.Builder(nameSessionCookie, httpCookie.value)
      .domain(EnvironmentConfigurationHolder.configuration.host)
      .build()
    SelenideCustomDriver.getDriver().manage().deleteCookieNamed(nameSessionCookie)
    SelenideCustomDriver.getDriver().manage().addCookie(seleniumCookie)
    Selenide.open(environmentConfiguration.getBaseUrl() + environmentConfiguration.crmStartEndpoint)
  }

  private fun getClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
  }

  private fun getRequest(): Request {
    return Request.Builder()
      .url(environmentConfiguration.getBaseUrl() + environmentConfiguration.crmSingInEndpoint)
      .addHeader("Authorization", Credentials.basic(environmentConfiguration.user!!, environmentConfiguration.pass!!))
      .post(CrmBodyGenerator(environmentConfiguration).authoriseCrm())
      .build()
  }
}

data class CrmUser(
  val id: String?, val userName: String?, val localizedRole: String?, val roleId: String?, val error: String?
)