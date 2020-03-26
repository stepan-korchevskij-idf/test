package api.mock.wiremock

import api.mock.Mock
import api.mock.MockBuilder
import api.mock.MockType
import utils.ResourceParser
import java.nio.file.NoSuchFileException

class WireMockBuilder : MockBuilder {
  override fun buildMock(mockType: MockType): Mock {
    return ResourceParser.getSelectedClassObjectFromJsonFile(mockType.pathname, Mock::class.java)
      ?: throw NoSuchFileException(mockType.pathname)
  }
}