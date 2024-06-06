package com.luismipalos.guideinabyss.views.signIn.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(viewModel: SignInViewModel, navController: NavController) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val pass: String by viewModel.pass.observeAsState(initial = "")
    val enabledSignin: Boolean by viewModel.enableSignin.observeAsState(initial = false)
    val isLoading: Boolean by viewModel.isLoading.observeAsState(initial = false)
    val signInFailed: Boolean by viewModel.failedSignIn.observeAsState(initial = false)
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
                DataTextField("Correo electrónico", Icons.Default.Mail, email) {
                    viewModel.onSigninChanged(it, pass)
                }
                Spacer(modifier = Modifier.height(5.dp))
                PasswordTextField(pass) {
                    viewModel.onSigninChanged(email, it)
                }
                Spacer(modifier = Modifier.height(25.dp))
                SignInButton(enabledSignin)
                    { coroutineScope.launch { viewModel.onSigninSelected(navController) } }
                Spacer(modifier = Modifier.height(5.dp))
                SignUpButton(true)
                    { coroutineScope.launch { viewModel.onSignupSelected(navController) } }

                if (signInFailed) {
                    AlertDialog(
                        onDismissRequest = {},
                        title = {Text("Inicio de sesión fallido")},
                        text = {Text("Las credenciales no son correctas.")},
                        confirmButton = {
                            Button(
                                onClick = {viewModel.dismissErrorDialog()}
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

