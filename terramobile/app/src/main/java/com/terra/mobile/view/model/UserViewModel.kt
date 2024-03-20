package com.terra.mobile.view.model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.terra.mobile.AppActivity
import com.terra.mobile.data.UserState
import com.terra.mobile.model.AuthenticationRequest
import com.terra.mobile.retrofit.repository.UserRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    var userUiState:UserState by mutableStateOf(UserState.Loading)

    fun logUser(email: String, password: String, navController: NavHostController) {
        viewModelScope.launch {
            userUiState=UserState.Loading
            userUiState=try {
                UserState.Success(userRepository.authenticate(AuthenticationRequest(email, password)))
            }  catch (e: IOException) {
                UserState.Error
            } catch (e: HttpException) {
                UserState.Error
            }

            if (userUiState is UserState.Success)
            {
                val navstring = "home";
                navController.navigate(navstring)
                Log.w("USERTOKKEN", userUiState.toString())
            }
        }
    }

    fun register(
        email: String,
        password: String,
        firsName: String,
        lastName: String,
        navController: NavHostController
    ) {
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AppActivity)
                val userRope = application.container.userRepository
                UserViewModel(userRope)
            }
        }
    }
}