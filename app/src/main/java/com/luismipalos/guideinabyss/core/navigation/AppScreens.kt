package com.luismipalos.guideinabyss.core.navigation

sealed class AppScreens(val route: String) {
    //Aquí van las rutas.
    data object SignInScreen: AppScreens("signin_screen")
    data object SignUpScreen: AppScreens("signup_screen")
    data object LayersScreen: AppScreens("layers_screen")
    data object ArtifactsScreen: AppScreens("artifacts_screen")
    data object DelversScreen: AppScreens("delvers_screen")
    data object ProfileScreen: AppScreens("profile_screen")
}
