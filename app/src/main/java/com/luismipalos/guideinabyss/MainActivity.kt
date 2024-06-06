package com.luismipalos.guideinabyss

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.luismipalos.guideinabyss.core.navigation.Navigation
import com.luismipalos.guideinabyss.ui.theme.GuideInAbyssTheme
import com.luismipalos.guideinabyss.views.artifacts.presentation.ArtifactsViewModel
import com.luismipalos.guideinabyss.views.delvers.presentation.DelversViewModel
import com.luismipalos.guideinabyss.views.layers.presentation.LayersViewModel
import com.luismipalos.guideinabyss.views.profile.presentation.ProfileViewModel
import com.luismipalos.guideinabyss.views.signIn.presentation.SignInViewModel
import com.luismipalos.guideinabyss.views.signUp.presentation.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GuideInAbyssApplication : Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //Aquí van los viewModels by viewModels()
    private val signInViewModel: SignInViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val layersViewModel: LayersViewModel by viewModels()
    private val artifactsViewModel: ArtifactsViewModel by viewModels()
    private val delversViewModel: DelversViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuideInAbyssTheme {
                Navigation(
                    //Aquí se incluyen los viewModels del principio de la clase
                    signInViewModel,
                    signUpViewModel,
                    layersViewModel,
                    artifactsViewModel,
                    delversViewModel,
                    profileViewModel
                )
            }
        }
    }
}