package com.terra.mobile.retrofit

import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.model.AuthenticationResponse
import com.terra.mobile.model.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface Api {
    @GET("health")
    suspend fun getProductsList(): String

    @POST("api/vi/auth/authenticate")
    suspend fun getAuthList(@Body authRequest: AuthenticationRequest): AuthenticationResponse

    @POST("api/vi/auth/register")
    suspend fun registerNewUserApi(@Body regRequest: RegistrationRequest): AuthenticationResponse

    companion object {
        const val BASE_URL = "http://192.168.0.105:8081/"
    }
}

