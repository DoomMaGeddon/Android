package com.luismipalos.guideinabyss.views.artifacts.data.network

import android.util.Log
import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import javax.inject.Inject

class ArtifactsService @Inject constructor(private val client: ArtifactsClient) {
    suspend fun doGetArtifacts() : List<ArtifactDTO>? {
        val response = client.doGetArtifacts().body()

        Log.i("Artifacts: ", response.toString())

        return response
    }

    suspend fun doPostArtifact(token: String, newArtifact: ArtifactDTO) : Boolean {
        val response = client.doPostArtifact(token, newArtifact).body()

        Log.i("New artifact: ", response?.status ?: "failed")

        return response?.status == "200"
    }
}