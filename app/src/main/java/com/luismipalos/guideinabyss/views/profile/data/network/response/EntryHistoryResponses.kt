package com.luismipalos.guideinabyss.views.profile.data.network.response

import com.google.gson.annotations.SerializedName
import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO

data class FaunaHistoryResponse(
    @SerializedName("fauna") val faunaHistory: List<FaunaDTO>
)

data class FloraHistoryResponse(
    @SerializedName("flora") val floraHistory: List<FloraDTO>
)

data class ArtifactHistoryResponse(
    @SerializedName("artifacts") val artifactHistory: List<ArtifactDTO>
)

data class DelverHistoryResponse(
    @SerializedName("delvers") val delverHistory: List<DelverDTO>
)