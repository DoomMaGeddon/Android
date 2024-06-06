package com.luismipalos.guideinabyss.views.profile.data.network.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
)