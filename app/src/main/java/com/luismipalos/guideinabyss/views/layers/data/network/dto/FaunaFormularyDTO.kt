package com.luismipalos.guideinabyss.views.layers.data.network.dto

data class FaunaFormularyDTO (
    var foto: String = "",
    var original: Boolean = true,
    var creadorEmail: String = "",
    var nombre: String = "",
    var especie: String = "",
    var habitat: Int = 1,
    var peligro: String = "",
    var dieta: String = "",
    var descripcion: String = "",
)