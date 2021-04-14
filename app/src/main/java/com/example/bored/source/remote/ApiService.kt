package com.example.bored.source.remote

import com.example.bored.source.responses.ActivityResponse
import com.example.bored.utils.Constants.BASE_URL
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("/api/activity/")
    suspend fun getRandomActivity() : Response<ActivityResponse>


    companion object {
        private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getApiServiceInstance() = retrofit.create(ApiService::class.java)
    }
}