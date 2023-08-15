package com.example.moviemaster.data.models.response


import com.example.moviemaster.common.resource.Resource
import retrofit2.Response
import java.io.IOException

abstract class BaseResponse (){
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): Resource<T> {
        val response = apiCall()
        try {
            if (response.isSuccessful) {
                response.body()?.let { resultResponse ->
                    return Resource.Success(resultResponse)
                }
            }

        } catch (ex: Exception) {
            return when (ex) {
                is IOException -> Resource.Error("Network Failure")
                else -> Resource.Error("Conversion Error")
            }
        }

        return Resource.Error("Unknown Error")
    }

}