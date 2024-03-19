package com.terra.mobile.composable

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.terra.mobile.ui.theme.TerramobileTheme
import com.terra.mobile.view.model.MapsViewModel
import com.terra.mobile.view.model.UserViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeCoposable(
    viewModel: UserViewModel?,
    navController: NavHostController
) {
    TerramobileTheme {
        if (viewModel != null) {
            //Text(text = "Wellcome to home your token is: ${viewModel.authToken}")
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                ) {
                    MapScreen()
                }

        }
    }
}