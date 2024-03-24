package com.terra.mobile.retrofit

import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.model.AuthenticationResponse
import com.terra.mobile.model.RegistrationRequest
import com.terra.mobile.model.SoilPointDTO
import com.terra.mobile.model.userModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface Api {
    @GET("health")
    suspend fun getProductsList(): String

    //Auth Apis
    @POST("api/vi/auth/authenticate")
    suspend fun getAuthList(@Body authRequest: AuthenticationRequest): AuthenticationResponse

    @POST("api/vi/auth/register")
    suspend fun registerNewUserApi(@Body regRequest: RegistrationRequest): AuthenticationResponse

    // Soil Apis
    @GET("soil")
    suspend fun getTestSoil(@Header("Authorization") token: String): List<SoilPointDTO>

    //User Apis
    @GET("user")
    suspend fun getUserData(@Header("Authorization") token: String): userModel

    companion object {
        const val BASE_URL = "http://192.168.0.105:8081/"
    }
}

