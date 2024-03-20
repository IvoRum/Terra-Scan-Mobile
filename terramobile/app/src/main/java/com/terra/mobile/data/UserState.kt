package com.terra.mobile.data

import com.terra.mobile.model.AuthenticationResponse

sealed interface UserState {
    data class Success(val authResponse: AuthenticationResponse) : UserState
    object Error : UserState
    object Loading : UserState
}
