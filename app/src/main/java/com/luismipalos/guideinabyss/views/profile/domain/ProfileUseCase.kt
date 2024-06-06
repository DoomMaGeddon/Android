package com.luismipalos.guideinabyss.views.profile.domain

import com.luismipalos.guideinabyss.views.profile.data.ProfileRepository
import com.luismipalos.guideinabyss.views.profile.data.network.dto.EntryHistoryDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.FormularyDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.ProfileDTO
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val networkRepository: ProfileRepository
) {
    suspend operator fun invoke(token: String, user: ProfileDTO) : Boolean =
        networkRepository.update(token, user)

    suspend operator fun invoke(token: String, formulary: FormularyDTO) : Boolean =
        networkRepository.submitFormulary(token, formulary)

    suspend operator fun invoke(token: String) : Boolean = networkRepository.signOut(token)

    suspend operator fun invoke(token: String, placeholder: String) : EntryHistoryDTO =
        networkRepository.getEntryHistory(token)
}