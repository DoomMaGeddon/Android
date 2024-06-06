package com.luismipalos.guideinabyss.views.signUp.data.network.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse (
    @SerializedName("email") val email: String
)