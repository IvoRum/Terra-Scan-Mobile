package com.terra.mobile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.terra.mobile.view.model.UserViewModel

@Composable
fun ProfileCoposable(
    userView: UserViewModel
) {
    val user = userView.userData.collectAsState()
    Column {
        Text(text = "First Name: ${user.value.firstName}")
        Text(text = "Last Name: ${user.value.lastName}")
        Text(text = "Email: ${user.value.email}")
    }
}