package com.example.bored.source.responses

data class ActivityResponse(
    val activity : String? = null,
    val accessibility : Double = 0.0,
    val type : String? = null,
    val participants : Int = 1,
    val price : Double = 0.0,
    val key : String? = null
)