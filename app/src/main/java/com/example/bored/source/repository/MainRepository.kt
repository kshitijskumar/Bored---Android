package com.example.bored.source.repository

import com.example.bored.source.remote.ApiService
import com.example.bored.utils.InjectorUtils.providesApiService

class MainRepository(
    private val api : ApiService = providesApiService()
) : BaseRepository() {

    suspend fun getRandomActivity() = safeApiCall {
        api.getRandomActivity()
    }
}