package com.luismipalos.guideinabyss.views.layers.data.network.dto

data class FloraFormularyDTO (
    var foto: String = "",
    var original: Boolean = true,
    var creadorEmail: String = "",
    var nombre: String = "",
    var especie: String = "",
    var habitat: Int = 1,
    var descripcion: String = "",
)