package com.example.bored.source.repository

import com.example.bored.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> safeApiCall(call : suspend () -> Response<T>) : Result<T> {
        return withContext(Dispatchers.IO){
            val response = call.invoke()

            if(response.isSuccessful && response.body() != null) {
                Result.Success(response.body())
            }
            Result.Error(response.message())
        }
    }
}