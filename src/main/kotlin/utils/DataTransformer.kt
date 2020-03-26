package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

fun transformDataToJson(data: Any): String =
  ObjectMapper().registerModule(KotlinModule()).writeValueAsString(data)

fun <T> transformJsonToAny(json: String?, objectClass: Class<T>): T? =
  json?.let { ObjectMapper().registerModule(KotlinModule()).readValue(it, objectClass) }