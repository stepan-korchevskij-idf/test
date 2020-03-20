package api.client

import api.client.data.HeaderType
import java.net.HttpCookie
import java.util.*

class CustomResponse {
  var body: String? = null
  var headers: Map<String, List<String>> = TreeMap<String, List<String>>()

  fun getCookies(): Map<String, String> {
    return headers[HeaderType.COOKIE.text]
      ?.map { textCookieHeader -> HttpCookie.parse(textCookieHeader)[0] }
      ?.map { httpCookie -> httpCookie.name to httpCookie.value }
      ?.toMap()
      ?: mapOf()
  }
}