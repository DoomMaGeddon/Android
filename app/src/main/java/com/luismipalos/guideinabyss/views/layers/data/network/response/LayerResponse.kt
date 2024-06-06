package com.luismipalos.guideinabyss.views.layers.data.network.response

import com.google.gson.annotations.SerializedName
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO

data class LayerResponse (
    @SerializedName("fauna") val fauna: List<FaunaDTO>,
    @SerializedName("flora") val flora: List<FloraDTO>
)