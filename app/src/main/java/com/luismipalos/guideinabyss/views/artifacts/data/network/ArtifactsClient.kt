package com.luismipalos.guideinabyss.views.artifacts.data.network

import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import com.luismipalos.guideinabyss.views.layers.data.network.response.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ArtifactsClient {
    @Headers(
        "Content-Type: application/json"
    )
    @GET("artifact")
    suspend fun doGetArtifacts() : Response<List<ArtifactDTO>?>

    @POST("artifact")
    suspend fun doPostArtifact(
        @Header("auth-token") token:String,
        @Body newArtifact: ArtifactDTO
    ) : Response<PostResponse>
}