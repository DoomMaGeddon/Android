package com.luismipalos.guideinabyss.views.signUp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.luismipalos.guideinabyss.R
import com.luismipalos.guideinabyss.ui.components.DataTextField
import com.luismipalos.guideinabyss.ui.components.Loading
import com.luismipalos.guideinabyss.ui.components.Logo
import com.luismipalos.guideinabyss.ui.components.PasswordTextField
import com.luismipalos.guideinabyss.ui.components.SignInButton
import com.luismipalos.guideinabyss.ui.components.SignUpButton
import com.luismipalos.guideinabyss.ui.theme.GiAPrimary
import com.luismipalos.guideinabyss.ui.theme.GiASecondary
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(viewModel: SignUpViewModel, navController: NavController) {
    val username: String by viewModel.username.observeAsState(initial = "")
    val email: String by viewModel.email.observeAsState(initial = "")
    val pass: String by viewModel.pass.observeAsState(initial = "")
    val enabledSignup: Boolean by viewModel.enableSignup.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val signUpFailed: Boolean by viewModel.failedSignUp.observeAsState(initial = false)
    val successfulSignUp: Boolean by viewModel.successfulSignUp.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (isLoading) {
        Loading()
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(GiAPrimary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .paint(
                        painter = painterResource(id = R.drawable.abyss),
                        contentScale = ContentScale.Crop
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Logo()
                Spacer(modifier = Modifier.height(20.dp))
                DataTextField("Nombre de usuario", Icons.Default.Face, username)
                    {viewModel.onSignupChanged(it, email, pass)}
                Spacer(modifier = Modifier.height(5.dp))
                DataTextField("Correo electrónico", Icons.Default.Mail, email)
                    {viewModel.onSignupChanged(username, it, pass)}
                Spacer(modifier = Modifier.height(5.dp))
                PasswordTextField(pass) {viewModel.onSignupChanged(username, email, it)}
                Spacer(modifier = Modifier.height(25.dp))
                SignUpButton(enabledSignup)
                    {coroutineScope.launch {viewModel.onSignupSelected()}}
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    color = GiAPrimary,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        shadow = Shadow(
                            color = GiASecondary,
                            offset = Offset(3.0f, 3.0f),
                            blurRadius = 3f
                        )
                    ),
                    text = "¿Ya tienes una cuenta?"
                )
                Spacer(modifier = Modifier.height(5.dp))
                SignInButton(true)
                    {coroutineScope.launch {viewModel.onSigninSelected(navController)}}

                if (signUpFailed) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = {Text("Registro fallido")},
                        text = {Text("No se ha podido registrar.")},
                        confirmButton = {
                            Button(
                                onClick = {viewModel.dismissErrorDialog()}
                            ) {
                                Text("Ok")
                            }
                        }
                    )
                }

                if (successfulSignUp) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = {Text("Registro exitoso")},
                        text = {Text("Se ha enviado un correo de confirmación.")},
                        confirmButton = {
                            Button(
                                onClick = {coroutineScope.launch {
                                    viewModel.dismissSuccessDialog(navController)}
                                }
                            ) {
                                Text("Ok")
                            }
                        }
                    )
                }
            }
        }
    }
}

