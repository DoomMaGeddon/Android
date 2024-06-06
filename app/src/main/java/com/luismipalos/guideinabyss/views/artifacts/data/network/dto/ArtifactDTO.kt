package com.luismipalos.guideinabyss.views.artifacts.data.network.dto

data class ArtifactDTO(
    val nombre: String,
    val foto: String,
    val grado: String,
    val efecto: String,
    val descripcion: String,
    val origen: String,
    val duenyoId: Int?,
    val original: Boolean,
    val creadorEmail: String?,
)
