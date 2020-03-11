package utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule

@Suppress("UNCHECKED_CAST")
fun <T> getSelectedClassObjectFromResourceFile(filePath: String, objectClass: Class<T>): T? {
  return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
    ObjectMapper(YAMLFactory())
      .registerModule(KotlinModule())
      .readValue(it, objectClass)
  }
}