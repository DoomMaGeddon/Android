package com.luismipalos.guideinabyss.views.profile.data.network

import android.util.Log
import com.luismipalos.guideinabyss.views.profile.data.network.dto.EntryHistoryDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.FormularyDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.ProfileDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileService @Inject constructor(private val client: ProfileClient) {
    suspend fun doUpdate(token: String, user: ProfileDTO) : Boolean {
        return withContext(Dispatchers.IO) {
            val response = client.doUpdate(token, user)

            Log.i("Update: ", response.body().toString())
            response.body()!!.status == "200"
        }
    }

    suspend fun doSubmitFormulary(token: String, user: FormularyDTO) : Boolean {
        return withContext(Dispatchers.IO) {
            val response = client.doSubmitFormulary(token, user)

            Log.i("Update: ", response.body().toString())
            response.body()!!.status == "200"
        }
    }

    suspend fun doSignOut(token: String) : Boolean {
        return withContext(Dispatchers.IO) {
            val response = client.doSignOut(token)

            Log.i("Log out: ", response.body()?.status ?: "failed")
            response.body()?.status == "200"
        }
    }

    suspend fun getEntryHistory(token: String) : EntryHistoryDTO {
        val response = EntryHistoryDTO()

        response.faunaHistory = client.doGetFaunaHistory(token).body()?.faunaHistory ?: emptyList()
        response.floraHistory = client.doGetFloraHistory(token).body()?.floraHistory ?: emptyList()
        response.artifactHistory = client.doGetArtifactHistory(token).body()?.artifactHistory ?: emptyList()
        response.delverHistory = client.doGetDelverHistory(token).body()?.delverHistory ?: emptyList()

        Log.i("Entry history: ", response.toString())
        return response
    }
}