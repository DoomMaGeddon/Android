package com.luismipalos.guideinabyss.views.layers.data.network.dto

data class FaunaDTO (
    val id: Int,
    val foto: String,
    val original: Boolean,
    val creadorEmail: String?,
    val nombre: String,
    val especie: String,
    val peligro: String,
    val dieta: String,
    val descripcion: String,
)