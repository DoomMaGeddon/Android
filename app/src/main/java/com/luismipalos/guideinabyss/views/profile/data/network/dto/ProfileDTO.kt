package com.luismipalos.guideinabyss.views.profile.data.network.dto

data class ProfileDTO(
    val email: String,
    val nombreUsuario: String,
    val contrasenya: String?,
    val fotoPerfil: String?,
    val descripcion: String?,
    val notificaciones: Boolean = true
)
