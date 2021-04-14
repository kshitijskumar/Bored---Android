package com.example.bored.utils

import com.example.bored.source.remote.ApiService

object InjectorUtils {

    fun providesApiService() = ApiService.getApiServiceInstance()
}