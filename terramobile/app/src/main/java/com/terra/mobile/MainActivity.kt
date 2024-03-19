package com.terra.mobile

import android.annotation.SuppressLint
import android.os.Build
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
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.graphics.Color
import com.terra.mobile.composable.HomeCoposable
import com.terra.mobile.composable.LogInCoposable
import com.terra.mobile.composable.RegisterCoposable
import com.terra.mobile.retrofit.repositoryimpl.AuthRepositoryImpl
import com.terra.mobile.retrofit.repositoryimpl.SoilRepositoryImpl
import com.terra.mobile.view.model.MapsViewModel


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
/*
    private val mapViewModel by viewModels<MapsViewModel>(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MapsViewModel(SoilRepositoryImpl(RetrofitInstance.api))
                        as T
            }
        }
    })

 */


    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
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
                    Scaffold(topBar = {
                        TopAppBar(
                            modifier = Modifier.background(Color.Gray),
                            title = {
                                //if (userData.value.id != 0 && userData.value.id != 404) {
                                    Row {
                                        IconButton(onClick = { /* do something */ }) {
                                            //DOTO ADD A BUTUN HERE??
                                        }
                                    }
                                //}
                            },
                            navigationIcon = {
                                IconButton(onClick = { navController.navigateUp() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "Localized description"
                                    )
                                }
                            },
                            actions = {
                                IconButton(onClick = { navController.navigate("profile") }) {
                                    if (userViewModel.authToken !="" ) {
                                        Row {
                                            Icon(
                                                Icons.Rounded.AccountCircle,
                                                contentDescription = "Your Profile"
                                            )
                                        }
                                    }
                                }
                            },
                        )
                    }) {
                        val productList = viewModel1.products.collectAsState().value
                        Log.w("Heath", productList);
                        NavHost(navController, startDestination = "landing") {
                            composable("landing") {
                                LandingCoposable(navController)
                            }
                            composable("register") {
                                RegisterCoposable(userViewModel, navController)
                            }
                            composable("login") {
                                LogInCoposable(userViewModel, navController)
                            }
                            composable("home") {
                                HomeCoposable(userViewModel, navController)
                            }
                        }
                    }

                }
            }
        }
    }
}
