package config.environment

import utils.getSelectedClassObjectFromResourceFile
import java.nio.file.NoSuchFileException

object EnvironmentConfigProvider {
  private const val nameFile = "environmentConfiguration.yaml"

  fun getConfiguration(): EnvironmentConfiguration {
    return getSelectedClassObjectFromResourceFile(nameFile, EnvironmentConfiguration::class.java)
      ?: throw NoSuchFileException(nameFile)
  }
}