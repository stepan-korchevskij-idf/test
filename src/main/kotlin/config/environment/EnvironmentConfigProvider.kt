package config.environment

import utils.getSelectedClassObjectFromResourceFile
import java.nio.file.NoSuchFileException

object EnvironmentConfigProvider {
  private const val NAME_FILE = "environmentConfiguration.yaml"

  fun getConfiguration(): EnvironmentConfiguration {
    return getSelectedClassObjectFromResourceFile(NAME_FILE, EnvironmentConfiguration::class.java)
      ?: throw NoSuchFileException(NAME_FILE)
  }
}