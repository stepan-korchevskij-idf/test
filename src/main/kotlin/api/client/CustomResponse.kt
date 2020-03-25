package api.client

import api.client.data.HeaderType
import java.net.HttpCookie
import java.util.*
import kotlin.collections.HashMap

class CustomResponse private constructor(
  val code: Int,
  val message: String?,
  val body: String?,
  val headers: Map<String, List<String>> = HashMap()
) {

  fun getCookies(): Map<String, String> {
    return headers[HeaderType.COOKIE.text]
      ?.map { textCookieHeader -> HttpCookie.parse(textCookieHeader)[0] }
      ?.map { httpCookie -> httpCookie.name to httpCookie.value }
      ?.toMap()
      ?: mapOf()
  }

  data class Builder(
    var code: Int,
    var message: String? = null,
    var body: String? = null,
    var headers: Map<String, List<String>> = TreeMap<String, List<String>>()
  ) {
    fun message(message: String?) = apply { this.message = message }
    fun body(body: String?) = apply { this.body = body }
    fun headers(headers: Map<String, List<String>>) = apply { this.headers = headers }
    fun build(): CustomResponse = CustomResponse(code, message, body, headers)
  }
}