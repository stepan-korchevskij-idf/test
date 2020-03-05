package core.utils

import java.io.FileNotFoundException
import java.util.*

internal fun loadProperty(nameResource: String): Properties {
  val resourceAsStream = Thread.currentThread().contextClassLoader.getResourceAsStream(nameResource)
    ?: throw FileNotFoundException("File '$nameResource' not found.")
  val result = Properties()
  result.load(resourceAsStream)
  return result
}