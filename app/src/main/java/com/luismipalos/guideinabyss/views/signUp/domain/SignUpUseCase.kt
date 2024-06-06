package com.luismipalos.guideinabyss.views.signUp.domain

import com.luismipalos.guideinabyss.views.signUp.data.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val networkRepository: SignUpRepository,
) {
    suspend operator fun invoke(name: String, user: String, password: String) : Boolean =
        networkRepository.signUp(name, user, password)
}