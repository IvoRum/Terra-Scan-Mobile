package com.terra.mobile

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.terra.mobile.composable.LandingCoposable
import com.terra.mobile.ui.theme.TerramobileTheme
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.terra.mobile.composable.HomeCoposable
import com.terra.mobile.composable.LogInCoposable
import com.terra.mobile.composable.RegisterCoposable
import com.terra.mobile.view.model.MapsViewModel


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TerramobileTheme {
                val mapViewModel: MapsViewModel =
                    viewModel(factory = MapsViewModel.Factory)
                val userViewModel: UserViewModel =
                    viewModel()

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
                                    if (userViewModel.authToken != "") {
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
                        NavHost(navController, startDestination = "landing") {
                            composable("landing") {
                                mapViewModel.getBgSoil()
                                LandingCoposable(navController)
                            }
                            composable("register") {
                                RegisterCoposable(userViewModel, navController)
                            }
                            composable("login") {
                                LogInCoposable(userViewModel, navController)
                            }
                            composable("home") {
                                Log.w("SOIL", mapViewModel.state._soil.multipolygon.toString())
                                HomeCoposable(userViewModel, mapViewModel, navController)
                            }
                        }
                    }

                }
            }
        }
    }
}
