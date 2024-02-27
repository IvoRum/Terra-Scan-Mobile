package com.terra.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.terra.mobile.composable.LogIn
import com.terra.mobile.ui.theme.TerramobileTheme
import com.terra.mobile.view.model.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<UserViewModel>()
        super.onCreate(savedInstanceState)
        setContent {
            TerramobileTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController, startDestination = "login") {
                        composable("login") {
                            LogIn(viewModel, navController)
                        }
                    }
                }
            }
        }
    }
}
