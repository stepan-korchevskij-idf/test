package api.mock

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.JsonNode

@JsonIgnoreProperties(ignoreUnknown = true)
data class Mock(
  var priority: Int?,
  var name: String?,
  var request: Request,
  var response: Response?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Request(
  var method: Method?,
  var urlPattern: String,
  var bodyPatterns: List<BodyPattern>?
) {
  enum class Method {
    GET, POST, DELETE, PUT, ANY
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Response(
  var status: Int?,
  var bodyFileName: String?,
  var headers: Map<String, String>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class BodyPattern(var equalToJson: JsonNode?)