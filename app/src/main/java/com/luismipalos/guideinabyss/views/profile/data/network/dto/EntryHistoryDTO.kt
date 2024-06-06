package com.luismipalos.guideinabyss.views.profile.data.network.dto

import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO

data class EntryHistoryDTO(
    var faunaHistory: List<FaunaDTO> = emptyList(),
    var floraHistory: List<FloraDTO> = emptyList(),
    var artifactHistory: List<ArtifactDTO> = emptyList(),
    var delverHistory: List<DelverDTO> = emptyList(),
)