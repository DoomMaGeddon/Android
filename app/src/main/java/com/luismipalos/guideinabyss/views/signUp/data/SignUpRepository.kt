package com.luismipalos.guideinabyss.views.signUp.data

import com.luismipalos.guideinabyss.views.signUp.data.network.SignUpService
import javax.inject.Inject

class SignUpRepository @Inject constructor(private val api: SignUpService) {
    suspend fun signUp(username: String, email: String, password: String) : Boolean =
        api.doSignUp(username, email, password)
}