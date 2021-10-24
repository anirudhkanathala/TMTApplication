package com.example.tmtapplication.api

import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * <h1>ResponseHandler</h1>
 * ResponseHandler is the handy util to get the success or failure from the response,
 */

open class ResponseHandler {
    /**
     * This method is used to determine the success
     * @param data Any Object
     * @return Resource<Any>
     */
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.Success(data)
    }

    /**
     * This method is used to determine the what type of error/failure
     * @param e Exception
     * @return Resource<Any>
     */
    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.Failure(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.Failure(getErrorMessage(500), null)
            else -> Resource.Failure(getErrorMessage(Int.MAX_VALUE), null)
        }
    }

    /**
     * This method is used to concat the error and status code
     * @param e Exception
     * @return Resource<Any>
     */
    private fun getErrorMessage(
        code: Int,
        msg: String? = "Something went wrong"
    ): String {
        return "$msg : $code"
    }

}
