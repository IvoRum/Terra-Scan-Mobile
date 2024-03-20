package com.terra.mobile.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.terra.mobile.model.AuthenticationResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserViewModel(
//    private val authRepo: AuthRepository
) : ViewModel() {

    private val _authToken = MutableStateFlow<AuthenticationResponse>(AuthenticationResponse(""))
    val authToken = _authToken.asStateFlow().value.accessToken

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    fun logUser(email: String, password: String,navController: NavHostController) {
        viewModelScope.launch {
            /*
            AuthenticationResponse("")
            authRepo.authenticate(AuthenticationRequest(email, password))
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            _showErrorToastChannel.send(true)
                        }

                        is Result.Success -> {
                            result.data?.let { products ->
                                _authToken.update { products }
                            }
                        }
                    }
                }

             */
            val navstring = "home";
            navController.navigate(navstring)
        }
    }

    fun register(email: String, password: String,firsName: String,lastName: String,navController: NavHostController) {
        viewModelScope.launch {
            /*
            AuthenticationResponse("")
            authRepo.register(RegistrationRequest(email,password,firsName,lastName))
                .collectLatest { result ->
                    when (result) {
                        is Result.Error -> {
                            _showErrorToastChannel.send(true)
                        }

                        is Result.Success -> {
                            result.data?.let { products ->
                                _authToken.update { products }
                            }
                        }
                    }
                }

             */
            val navstring = "home";
            navController.navigate(navstring)
        }
    }
}