package com.luismipalos.guideinabyss.views.layers.data.network.dto

data class LayerDTO (
    val fauna: List<FaunaDTO> = emptyList(),
    val flora: List<FloraDTO> = emptyList()
)