package api.mock.wiremock

import api.mock.DefaultMockService

class WireMockService(
  override val client: WireMockClient = WireMockClient(),
  override val builder: WireMockBuilder = WireMockBuilder()
) : DefaultMockService(client, builder)