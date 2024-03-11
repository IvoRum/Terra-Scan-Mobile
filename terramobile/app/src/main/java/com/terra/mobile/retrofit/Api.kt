package com.terra.mobile.retrofit

import retrofit2.http.GET

interface Api {
    @GET("health")
    suspend fun getProductsList(): String

    companion object {
        const val BASE_URL = "http://192.168.0.105:8081/"
    }
}

