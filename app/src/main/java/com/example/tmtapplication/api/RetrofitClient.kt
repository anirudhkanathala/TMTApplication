package com.example.tmtapplication.api

import com.example.tmtapplication.utils.TUtil
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * <h1>RetrofitClient</h1>
 * RetrofitClient is the client class to make all the network calls with the server.,
 */

class RetrofitClient {

    /**
     * This method returns the retrofit client
     * @return ApiInterfaceApiInterface
     */
    fun getClient(): ApiInterface {
        val adapter = Retrofit.Builder()
            .baseUrl("https://private-8ce77c-tmobiletest.apiary-mock.com/test/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClientWithInterceptor())
            .build()
        return adapter.create(ApiInterface::class.java)
    }

    /**
     * This method used to add interceptors witht he OkHttpClient in non-prod application.
     * @return OkHttpClient
     */
    private fun getOkHttpClientWithInterceptor(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        // Adding detailed network trace just for non prod.
        if (TUtil.isNonProd()) {
            val interceptorBody = HttpLoggingInterceptor()
            interceptorBody.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptorBody)
            builder.addNetworkInterceptor(StethoInterceptor())
        }

        return builder.build()
    }
}
