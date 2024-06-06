package com.luismipalos.guideinabyss.views.artifacts.data

import com.luismipalos.guideinabyss.views.artifacts.data.network.ArtifactsService
import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import javax.inject.Inject

class ArtifactsRepository @Inject constructor(private val api: ArtifactsService) {
    suspend fun getArtifacts() : List<ArtifactDTO>? = api.doGetArtifacts()

    suspend fun postArtifact(token: String, newArtifact: ArtifactDTO) : Boolean =
        api.doPostArtifact(token, newArtifact)
}