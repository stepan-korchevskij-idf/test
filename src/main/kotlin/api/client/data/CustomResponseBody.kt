package api.client.data

import okhttp3.MediaType

data class CustomResponseBody(val content: String? = null, val contentType: MediaType? = null)