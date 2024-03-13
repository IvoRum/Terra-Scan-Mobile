package com.terra.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.terra.mobile.composable.LandingCoposable
import com.terra.mobile.retrofit.RetrofitInstance
import com.terra.mobile.retrofit.repositoryimpl.HeathRepositoryImpl
import com.terra.mobile.ui.theme.TerramobileTheme
import com.terra.mobile.view.model.HealthViewModel
import com.terra.mobile.view.model.UserViewModel
import android.util.Log
import com.terra.mobile.composable.HomeCoposable
import com.terra.mobile.composable.LogInCoposable
import com.terra.mobile.composable.RegisterCoposable
import com.terra.mobile.retrofit.repositoryimpl.AuthRepositoryImpl


class MainActivity : ComponentActivity() {


    private val viewModel1 by viewModels<HealthViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HealthViewModel(HeathRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

    private val userViewModel by viewModels<UserViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(AuthRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })



    override fun onCreate(savedInstanceState: Bundle?) {
        //val viewModel by viewModels<UserViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            TerramobileTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val productList = viewModel1.products.collectAsState().value
                    Log.w("Heath", productList);
                    NavHost(navController, startDestination = "landing") {
                        composable("login") {
                            LandingCoposable(navController)
                        }
                        composable("register") {
                            RegisterCoposable(userViewModel, navController)
                        }
                        composable("login") {
                            LogInCoposable(userViewModel, navController)
                        }
                        composable("home"){
                            HomeCoposable(userViewModel,navController)
                        }
                    }
                }
            }
        }
    }
}
