package com.terra.mobile.retrofit.repository

import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.model.AuthenticationResponse
import com.terra.mobile.model.RegistrationRequest
import com.terra.mobile.model.userModel
import com.terra.mobile.retrofit.Api

interface UserRepository {
    suspend fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse
    suspend fun register(registrationRequest: RegistrationRequest): AuthenticationResponse
    suspend fun getUserData(token:String): userModel
}

class NetworkUserRepository(
    private val api: Api
) : UserRepository {
    override suspend fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse =
        api.getAuthList(authRequest)

    override suspend fun register(registrationRequest: RegistrationRequest): AuthenticationResponse =
        api.registerNewUserApi(registrationRequest)

    override suspend fun getUserData(token: String): userModel =api.getUserData(token)
}