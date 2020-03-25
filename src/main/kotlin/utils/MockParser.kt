package utils

import api.mock.Mock
import api.mock.StubType
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

fun getMockObjectFromResourceFile(stubType: StubType): Mock {
  return ObjectMapper(JsonFactory())
    .registerModule(KotlinModule())
    .readValue(Thread.currentThread().contextClassLoader.getResourceAsStream(stubType.pathname), Mock::class.java)
}