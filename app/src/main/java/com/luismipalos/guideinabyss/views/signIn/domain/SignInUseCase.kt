package com.luismipalos.guideinabyss.views.signIn.domain

import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.signIn.data.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val networkRepository: SignInRepository
) {
    suspend operator fun invoke(user: String, password: String, loggedUserDao: LoggedUserDao) : Boolean =
        networkRepository.signIn(user, password, loggedUserDao)
}