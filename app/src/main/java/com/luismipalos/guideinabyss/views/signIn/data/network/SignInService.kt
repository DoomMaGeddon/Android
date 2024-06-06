package com.luismipalos.guideinabyss.views.signIn.data.network

import android.util.Log
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUser
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.signIn.data.network.dto.UserDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SignInService @Inject constructor(private val client: SignInClient) {
    suspend fun doSignIn(user: String, password: String, loggedUserDao: LoggedUserDao) : Boolean {
        return withContext(Dispatchers.IO) {
            val response = client.doSignIn(UserDTO(user, password))

            loggedUserDao.deleteLoggedUser()
            if (!response.body()?.user?.email.isNullOrEmpty() &&
                !response.body()?.token.isNullOrEmpty())
            {
                Log.i("User:", response.body()?.user.toString())
                loggedUserDao.insertLoggedUser(
                    LoggedUser(
                        response.body()!!.token,
                        response.body()!!.user.email,
                        response.body()!!.user.nombreUsuario,
                        response.body()!!.user.fotoPerfil,
                        response.body()!!.user.descripcion,
                        response.body()!!.user.rol,
                        response.body()!!.user.experiencia,
                        response.body()!!.user.rangoId,
                        response.body()!!.user.notificaciones
                    )
                )
            }

            Log.i("Sign in: ", response.body()?.token ?: ("failed: " + response.body()))
            !response.body()?.token.isNullOrEmpty()
        }
    }
}