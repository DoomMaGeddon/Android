package com.luismipalos.guideinabyss.views.signUp.data.network

import android.util.Log
import com.luismipalos.guideinabyss.views.signUp.data.network.dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignUpService @Inject constructor(private val client: SignUpClient) {
    suspend fun doSignUp(username: String, email: String, password: String) : Boolean {
        return withContext(Dispatchers.IO) {
            val user = UserDTO(username, email, password)
            val response = client.doSignUp(user)

            Log.i("Sign up: ",
                response.body()?.email ?: ("failed, $user")
            )
            !response.body()?.email.isNullOrEmpty()
        }
    }
}
