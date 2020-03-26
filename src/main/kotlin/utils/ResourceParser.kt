package utils

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

object ResourceParser {

  fun <T> getSelectedClassObjectFromYamlFile(filePath: String, objectClass: Class<T>): T? {
    return getSelectedClassObjectFromResource(filePath, objectClass, YAMLFactory())
  }

  fun <T> getSelectedClassObjectFromJsonFile(filePath: String, objectClass: Class<T>): T? {
    return getSelectedClassObjectFromResource(filePath, objectClass, JsonFactory())
  }

  private fun <T> getSelectedClassObjectFromResource(
    filePath: String,
    objectClass: Class<T>,
    jsonFactory: JsonFactory
  ): T? {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(jsonFactory)
        .registerModule(KotlinModule())
        .readValue(it, objectClass)
    }
  }
}