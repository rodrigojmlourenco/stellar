package uk.co.stellar.data

import NeoFeedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidNeoWsAPI {

    @GET("neo/rest/v1/feed")
    suspend fun getFeed(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String,
    ): NeoFeedResponse
}
