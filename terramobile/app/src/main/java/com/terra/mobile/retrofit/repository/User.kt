package com.terra.mobile.retrofit.repository

import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.model.AuthenticationResponse
import com.terra.mobile.model.SoilPointDTO
import com.terra.mobile.retrofit.Api

interface UserRepository {
    suspend fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse
}

class NetworkUserRepository(
    private val api: Api
) : UserRepository {
    override suspend fun authenticate(authRequest: AuthenticationRequest): AuthenticationResponse =
        api.getAuthList(authRequest)
}