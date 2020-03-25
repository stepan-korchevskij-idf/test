package api.mock.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Mock {
  var priority: Int? = null
  var name: String? = null
  var request: Request? = null
  var response: Response? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Request {
  var method: String? = null
  var urlPattern: String? = null
  var bodyPatterns: List<BodyPattern>? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Response {
  var status: Int? = null
  var bodyFileName: String? = null
  var headers: Map<String, String>? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class BodyPattern {
  var equalToJson: EqualToJson? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class EqualToJson {
  var login: String? = null
  var password: String? = null
  var captcha: String? = null
}