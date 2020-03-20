package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

fun transformDataToJsonString(data: Any): String {
  return ObjectMapper().registerModule(KotlinModule()).writeValueAsString(data)
}