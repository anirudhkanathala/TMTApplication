package com.example.tmtapplication.api
/**
 * <h1>Resource</h1>
 * Resource is the sealed class to matain the state of the request,
 *
 */

sealed class Resource<out T> {
    data class Loading<out T>(val partialData: T? = null) :
        Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val errorMessage: String? = "", val throwable: Throwable? = null) :
        Resource<T>()

}
