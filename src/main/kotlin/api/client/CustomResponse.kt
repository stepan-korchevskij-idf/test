package api.client

import api.client.data.CustomResponseBody
import okhttp3.Response
import java.util.*

class CustomResponse {
  var body: CustomResponseBody? = null
  var headers: Map<String, List<String>> = TreeMap<String, List<String>>()
}

fun Response.toCustomResponse(): CustomResponse {
  return CustomResponse().also {
    it.body = CustomResponseBody(body?.string(), body?.contentType())
    it.headers = headers.toMultimap()
  }
}