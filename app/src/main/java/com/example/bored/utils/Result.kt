package com.example.bored.utils

sealed class Result<out T> {

    data class Success<T>(val data : T ) : Result<T>()
    object Idle : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Error(val errorMsg : String) : Result<Nothing>()
}
