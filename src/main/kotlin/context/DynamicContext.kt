package context

import api.client.CustomResponse
import api.client.data.HeaderType
import org.apache.logging.log4j.LogManager
import java.net.HttpCookie

class DynamicContext {
  private val logger = LogManager.getLogger(this.javaClass.simpleName)
  val cookie = HashMap<String, String>()

  fun addCookie(customResponse: CustomResponse) {
    customResponse.headers[HeaderType.COOKIE.text]?.map { text -> HttpCookie.parse(text) }
      ?.forEach { httpCookies ->
        val httpCookie = httpCookies[0]
        logger.info("Adding cookie: '${httpCookie.name}': '${httpCookie.value}'")
        cookie[httpCookie.name] = httpCookie.value
      }
  }
}