package com.example.tmtapplication.api

import com.google.gson.JsonObject
import retrofit2.http.GET

/**
 * <h1>ApiInterface</h1>
 * ApiInterface is the network interface class for the TMT,
 * where we used suspend methods to take advantage of coroutines.
 */

interface ApiInterface {

    @GET("home")
    suspend fun getTCards(
    ): JsonObject
}
