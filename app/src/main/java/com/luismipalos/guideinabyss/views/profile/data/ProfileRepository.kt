package com.luismipalos.guideinabyss.views.profile.data

import com.luismipalos.guideinabyss.views.profile.data.network.ProfileService
import com.luismipalos.guideinabyss.views.profile.data.network.dto.EntryHistoryDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.FormularyDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.ProfileDTO
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ProfileService) {
    suspend fun update(token: String, user: ProfileDTO) : Boolean = api.doUpdate(token, user)

    suspend fun submitFormulary(token: String, formulary: FormularyDTO) : Boolean =
        api.doSubmitFormulary(token, formulary)

    suspend fun signOut(token: String) : Boolean = api.doSignOut(token)

    suspend fun getEntryHistory(token: String) : EntryHistoryDTO = api.getEntryHistory(token)
}