package com.example.tmtapplication.api.datasource

import com.example.tmtapplication.api.Resource
import com.example.tmtapplication.api.ResponseHandler
import com.example.tmtapplication.api.RetrofitClient
import com.example.tmtapplication.model.TMTCard
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

/**
 * <h1>TDataSource</h1>
 * TDataSource is used to act like intermediate layer between Server and client
 *
 */
class TDataSource(private val retrofitClient: RetrofitClient) {

    private var responseHandler = ResponseHandler()

    /**
     * Load cards from the server
     * @return Resource<List<TMTCard>>
     */
    suspend fun getTMTCards(): Resource<List<TMTCard>> {
        class Token : TypeToken<List<TMTCard>>()
        return try {

            var cardData = retrofitClient.getClient().getTCards().get("page") as JsonObject

            responseHandler.handleSuccess(
                Gson().fromJson(cardData.get("cards") as JsonArray, Token().type)
            )
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }

}
