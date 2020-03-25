package api.mock

import api.client.data.HttpMethod

class Mock private constructor(
  var priority: Int?,
  var name: String?,
  var httpMethod: HttpMethod?,
  var request: Request,
  var response: Response
)

class Request private constructor(
  var urlPattern: String,
  var bodyPatterns: String?,
  var headers: Map<String, List<String>> = HashMap()
)

class Response private constructor(
  val status: Int,
  var body: String?,
  var headers: Map<String, List<String>> = HashMap()
)