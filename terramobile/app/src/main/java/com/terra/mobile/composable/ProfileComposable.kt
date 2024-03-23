package com.terra.mobile.composable

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.terra.mobile.ui.theme.TerramobileTheme
import com.terra.mobile.view.model.MapsViewModel
import com.terra.mobile.view.model.UserViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileCoposable(
    userView: UserViewModel,
    navController: NavHostController
) {
    TerramobileTheme {

    }
}