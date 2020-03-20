package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.Response

object JsonParser {
  private val mapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

  fun <T> fromBodyResponse(response: Response, objectClass: Class<T>): T {
    return mapper.readValue(response.body?.string(), objectClass)
  }
}