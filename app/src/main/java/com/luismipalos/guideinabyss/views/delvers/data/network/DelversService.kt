package com.luismipalos.guideinabyss.views.delvers.data.network

import android.util.Log
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import javax.inject.Inject

class DelversService @Inject constructor(private val client: DelversClient) {
    suspend fun doGetDelvers() : List<DelverDTO>? {
        val response = client.doGetDelvers().body()

        Log.i("Delvers: ", response.toString())

        return response
    }

    suspend fun doPostDelver(token: String, newDelver: DelverDTO) : Boolean {
        val response = client.doPostDelver(token, newDelver).body()

        Log.i("New delver: ", response?.status ?: "failed")

        return response?.status == "200"
    }
}