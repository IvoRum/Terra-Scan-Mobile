package com.terra.mobile.model

data class AuthenticationResponse(val accessToken: String)

data class AuthenticationRequest(val email:String,val password:String)