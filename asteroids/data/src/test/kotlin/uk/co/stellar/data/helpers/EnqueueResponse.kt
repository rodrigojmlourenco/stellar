package uk.co.stellar.data.helpers

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

internal fun MockWebServer.enqueueResponse(status: Int, body: String) {
    val response = MockResponse()
    response.setResponseCode(status)
    response.setBody(body)

    this.enqueue(response)
}
