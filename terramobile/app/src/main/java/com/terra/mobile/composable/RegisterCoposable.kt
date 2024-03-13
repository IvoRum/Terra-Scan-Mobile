package com.terra.mobile.composable

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.terra.mobile.R
import com.terra.mobile.ui.theme.TerramobileTheme
import com.terra.mobile.view.model.UserViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun RegisterCoposable(viewModel: UserViewModel?, navController: NavHostController) {
    var currentProgress by remember { mutableStateOf(0f) }
    var loading by remember { mutableStateOf(false) }
    var userName by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passworComfirm by rememberSaveable { mutableStateOf("") }
    val isTextFieldEmpty by derivedStateOf {
        userName.isBlank()
    }
    val isTextFieldEmptyPassword by derivedStateOf {
        password.isBlank()
    }

    TerramobileTheme {
        TerramobileTheme {
            Box(
                modifier = with(Modifier) {
                    fillMaxHeight()
                        .paint(
                            painterResource(id = R.drawable.globe_3),
                            contentScale = ContentScale.FillHeight,
                            colorFilter = ColorFilter.tint(Color.Gray, blendMode = BlendMode.Darken)
                        )

                })
            {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Terra Scan",
                        fontSize = TextUnit(12f, TextUnitType.Em),
                        fontFamily = FontFamily(Font(R.font.bowlby_one)),
                        color = terraWhite
                    )
                    TextField(
                        value = userName,
                        modifier = Modifier.background(terraWhite),
                        onValueChange = { userName = it },
                        label = { Text("Email") }
                    )
                    TextField(
                        value = firstName,
                        modifier = Modifier.background(terraWhite),
                        onValueChange = { firstName = it },
                        label = { Text("First name") }
                    )
                    TextField(
                        value = lastName,
                        modifier = Modifier.background(terraWhite),
                        onValueChange = { lastName = it },
                        label = { Text("Last name") }
                    )
                    TextField(
                        value = password,
                        modifier = Modifier.background(terraWhite),
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    TextField(
                        value = passworComfirm,
                        modifier = Modifier.background(terraWhite),
                        onValueChange = { passworComfirm = it },
                        label = { Text("Confirm password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    if (isTextFieldEmpty) {
                        Text("Please enter text", color = Color.Red)
                    }
                    Button(
                        onClick = {
                            if (!isTextFieldEmpty && !isTextFieldEmptyPassword) {
                                loading = true

                                if (viewModel != null) {
                                    RegisternAction(
                                        viewModel,
                                        userName,
                                        password,
                                        navController
                                    ) { progress ->
                                        currentProgress = progress
                                    }
                                }
                                loading = false


                            }
                        },
                        modifier = Modifier.padding(10.dp),
                        colors = ButtonDefaults.buttonColors(terraWhite),
                    )
                    {
                        Text(text = "Log in", color = Color.Black)

                    }
                    Button(
                        onClick = {

                            loading = true
                            if (viewModel != null) {
                                RegisternAction(
                                    viewModel,
                                    "ivoAdmin@mail.com",
                                    "12345678",
                                    navController
                                ) { progress ->
                                    currentProgress = progress
                                }
                            }
                            loading = false


                        },
                        modifier = Modifier.padding(10.dp), colors = ButtonDefaults.buttonColors(
                            Color.Transparent,
                            Color.Transparent, Color.Transparent, Color.Transparent
                        )
                    )
                    {
                        Text(text = "Log in")
                    }
                }
            }
        }
    }
}

private fun RegisternAction(
    viewModel: UserViewModel,
    email: String,
    password: String,
    navController: NavHostController,
    updateProgress: (Float) -> Unit
) {
    updateProgress(40F)
    viewModel.logUser(email, password, navController)
}

@Preview
@Composable
fun RegisterPreview() {
    val navController = rememberNavController()
    RegisterCoposable(viewModel = null, navController = navController)
}