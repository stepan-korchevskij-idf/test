package api.client

import api.client.data.HttpMethod
import api.client.data.HttpMethod.*
import okhttp3.Request
import okhttp3.RequestBody

class CustomRequest {
  private var url: String? = null
  private var headers = ArrayList<Pair<String, String>>()
  private var body: RequestBody? = null
  private var method: HttpMethod? = null

  fun generateOkHttpRequest(): Request {
    return Request.Builder().apply {
        url(url!!)
        headers.forEach { addHeader(it.first, it.second) }
        when (method) {
          GET -> get()
          POST -> post(body!!)
          PUT -> put(body!!)
          DELETE -> delete(body)
        }
      }
      .build()
  }

  class Builder {
    private var url: String? = null
    private val headers = ArrayList<Pair<String, String>>()
    private var body: RequestBody? = null
    private var method: HttpMethod? = null

    fun url(url: String): Builder {
      this.url = url
      return this
    }

    fun addHeader(name: String, value: String): Builder {
      this.headers.add(Pair(name, value))
      return this
    }

    fun addHeaders(headers: Map<String, String>): Builder {
      headers.forEach { (t, u) -> this.headers.add(Pair(t, u)) }
      return this
    }

    fun body(body: RequestBody): Builder {
      this.body = body
      return this
    }

    fun method(method: HttpMethod): Builder {
      this.method = method
      return this
    }

    fun build(): CustomRequest {
      return CustomRequest().also {
        it.url = url
        it.headers = headers
        it.body = body
        it.method = method
      }
    }
  }
}