package com.luismipalos.guideinabyss.views.signIn.data.network.response

import com.google.gson.annotations.SerializedName
import com.luismipalos.guideinabyss.views.signIn.data.network.dto.UserResponseDTO

data class SignInResponse(
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: UserResponseDTO,
)