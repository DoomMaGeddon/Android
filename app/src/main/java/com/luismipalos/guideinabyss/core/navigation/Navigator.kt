package com.luismipalos.guideinabyss.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luismipalos.guideinabyss.views.artifacts.presentation.ArtifactsScreen
import com.luismipalos.guideinabyss.views.artifacts.presentation.ArtifactsViewModel
import com.luismipalos.guideinabyss.views.delvers.presentation.DelversScreen
import com.luismipalos.guideinabyss.views.delvers.presentation.DelversViewModel
import com.luismipalos.guideinabyss.views.layers.presentation.LayersScreen
import com.luismipalos.guideinabyss.views.layers.presentation.LayersViewModel
import com.luismipalos.guideinabyss.views.profile.presentation.ProfileScreen
import com.luismipalos.guideinabyss.views.profile.presentation.ProfileViewModel
import com.luismipalos.guideinabyss.views.signIn.presentation.SignInScreen
import com.luismipalos.guideinabyss.views.signIn.presentation.SignInViewModel
import com.luismipalos.guideinabyss.views.signUp.presentation.SignUpScreen
import com.luismipalos.guideinabyss.views.signUp.presentation.SignUpViewModel

@Composable
fun Navigation(
    //Aquí van los viewVodels
    signInViewModel: SignInViewModel,
    signUpViewModel: SignUpViewModel,
    layersViewModel: LayersViewModel,
    artifactsViewModel: ArtifactsViewModel,
    delversViewModel: DelversViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SignInScreen.route) {
        //Ruta de pantallas simples:
        composable(route = AppScreens.SignInScreen.route) {
            SignInScreen((signInViewModel), navController)
        }

        composable(route = AppScreens.SignUpScreen.route) {
            SignUpScreen((signUpViewModel), navController)
        }

        composable(route = AppScreens.LayersScreen.route) {
            LayersScreen((layersViewModel), navController)
        }

        composable(route = AppScreens.ArtifactsScreen.route) {
            ArtifactsScreen((artifactsViewModel), navController)
        }

        composable(route = AppScreens.DelversScreen.route) {
            DelversScreen((delversViewModel), navController)
        }

        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen((profileViewModel), navController)
        }

        //Ruta de pantallas con argumentos
        //composable(route = AppScreens.DetailScreen.route + "/{dish}",
        //   arguments = listOf(navArgument(name = "dish") {
        //        type = NavType.StringType
        //    })) {
        //        DetailsScreen(detailsViewModel, navController, it.arguments?.getString("dish"))
        //}
    }
}