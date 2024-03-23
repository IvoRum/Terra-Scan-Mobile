package com.terra.mobile.model

data class AuthenticationResponse(val access_token: String)

data class AuthenticationRequest(val email: String, val password: String)

data class RegistrationRequest(
    val email: String, val password: String, val firstName: String, val lastName: String
)