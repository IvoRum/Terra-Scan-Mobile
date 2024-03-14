package com.terra.mobile.retrofit.repository

import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.model.AuthenticationResponse
import com.terra.mobile.model.RegistrationRequest
import com.terra.mobile.retrofit.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun authenticate(authRequst: AuthenticationRequest): Flow<Result<AuthenticationResponse>>
    suspend fun register(regRequest: RegistrationRequest): Flow<Result<AuthenticationResponse>>
}