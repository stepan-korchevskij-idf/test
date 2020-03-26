package config.environment

import utils.ResourceParser
import java.nio.file.NoSuchFileException

object EnvironmentConfigProvider {
  private const val NAME_FILE = "environmentConfiguration.yaml"

  fun getConfiguration(): EnvironmentConfiguration {
    return ResourceParser.getSelectedClassObjectFromYamlFile(NAME_FILE, EnvironmentConfiguration::class.java)
      ?: throw NoSuchFileException(NAME_FILE)
  }
}