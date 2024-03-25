package com.terra.mobile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.terra.mobile.R
import com.terra.mobile.view.model.UserViewModel

@Composable
fun ProfileCoposable(
    userView: UserViewModel
) {
    val user = userView.userData.collectAsState()
    Column {
        Spacer(modifier = Modifier.height(13.dp))
        Spacer(modifier = Modifier.width(13.dp))
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Profile picer"
        )
        Text(text = "First Name: ${user.value.firstName}")
        Text(text = "Last Name: ${user.value.lastName}")
        Text(text = "Email: ${user.value.email}")
    }
}