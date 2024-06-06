package com.luismipalos.guideinabyss.views.artifacts.domain

import com.luismipalos.guideinabyss.views.artifacts.data.ArtifactsRepository
import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import javax.inject.Inject

class ArtifactsUseCase @Inject constructor(
    private val networkRepository: ArtifactsRepository
) {
    suspend operator fun invoke() : List<ArtifactDTO>? = networkRepository.getArtifacts()

    suspend operator fun invoke(token: String, newArtifact: ArtifactDTO) : Boolean =
        networkRepository.postArtifact(token, newArtifact)
}