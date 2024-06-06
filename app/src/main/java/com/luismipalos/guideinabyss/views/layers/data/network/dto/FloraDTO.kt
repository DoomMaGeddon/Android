package com.luismipalos.guideinabyss.views.layers.data.network.dto

data class FloraDTO (
    val id: Int,
    val foto: String,
    val original: Boolean,
    val creadorEmail: String?,
    val nombre: String,
    val especie: String,
    val descripcion: String,
)