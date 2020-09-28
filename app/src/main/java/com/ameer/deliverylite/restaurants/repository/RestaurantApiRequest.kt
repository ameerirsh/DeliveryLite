package com.ameer.deliverylite.restaurants.repository
import android.util.Log
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object NetworkError: Result<Nothing>()
}


open class RestaurantApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>) : T?{
        val response = call.invoke()
        if(response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(response.code().toString())
        }
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null
        try {
            when(result) {
                is Result.Success ->
                    data = result.data

            }
        } catch(throwable: Throwable) {
            when(result) {
                is IOException -> Result.NetworkError
                is HttpException -> Result.NetworkError
                is Result.Success ->
                    data = result.data
                is Result.Error -> {
                    Log.d("repository", "$errorMessage & Exception - ${result.exception}")
                }
            }
        }
        return data
    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T>{
        val response = call.invoke()
        if(response.isSuccessful) return Result.Success(response.body()!!)
        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}
class ApiException(message: String) : IOException(message) {

}