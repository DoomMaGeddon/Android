package com.luismipalos.guideinabyss.views.signIn.data

import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.signIn.data.network.SignInService
import javax.inject.Inject

class SignInRepository @Inject constructor(private val api: SignInService) {
    suspend fun signIn(user: String, password: String, loggedUserDao: LoggedUserDao) :
            Boolean = api.doSignIn(user, password, loggedUserDao)
}