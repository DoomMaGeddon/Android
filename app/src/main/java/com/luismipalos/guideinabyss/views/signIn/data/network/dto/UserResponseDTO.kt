package com.luismipalos.guideinabyss.views.signIn.data.network.dto

data class UserResponseDTO(
    val email: String,
    val nombreUsuario: String,
    val fotoPerfil: String?,
    val descripcion: String?,
    val rol: String,
    val experiencia: Int,
    val rangoId: Int?,
    val notificaciones: Boolean
)
