package api.client

import api.client.data.HttpMethod

class CustomRequest private constructor(
  val url: String,
  val method: HttpMethod,
  val headers: List<Pair<String, String>> = ArrayList(),
  val body: Any? = null,
  val credentials: Credentials? = null
) {

  data class Builder(
    var url: String,
    var method: HttpMethod,
    var headers: ArrayList<Pair<String, String>> = ArrayList(),
    var body: Any? = null,
    var credentials: Credentials? = null
  ) {

    fun addHeader(name: String, value: String) = apply {
      this.headers.add(Pair(name, value))
    }

    fun body(body: Any) = apply { this.body = body }
    fun credentials(credentials: Credentials) = apply { this.credentials = credentials }
    fun build() = CustomRequest(url, method, headers, body, credentials)
  }
}

data class Credentials(val username: String, val password: String)