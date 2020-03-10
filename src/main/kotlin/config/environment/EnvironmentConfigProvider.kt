package config.environment

import core.utils.getSelectedClassObjectFromResourceFile
import java.nio.file.NoSuchFileException

object EnvironmentConfigProvider {
  private const val nameFile = "environmentConfiguration.yaml"

  internal fun getConfiguration(): EnvironmentConfiguration {
    return getSelectedClassObjectFromResourceFile(nameFile, EnvironmentConfiguration::class.java)
      ?: throw NoSuchFileException(nameFile)
  }
}