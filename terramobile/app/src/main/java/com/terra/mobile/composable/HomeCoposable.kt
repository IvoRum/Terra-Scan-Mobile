package com.terra.mobile.composable

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.terra.mobile.ui.theme.TerramobileTheme
import com.terra.mobile.view.model.UserViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeCoposable(viewModel: UserViewModel?, navController: NavHostController) {
    TerramobileTheme {
        if (viewModel != null) {
            Text(text = "Wellcome to home your token is: ${viewModel.authToken}")
        }
    }
}