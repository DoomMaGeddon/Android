package com.luismipalos.guideinabyss.views.delvers.data

import com.luismipalos.guideinabyss.views.delvers.data.network.DelversService
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import javax.inject.Inject

class DelversRepository @Inject constructor(private val api: DelversService) {
    suspend fun getDelvers() : List<DelverDTO>? = api.doGetDelvers()

    suspend fun postDelver(token: String, newDelver: DelverDTO) : Boolean =
        api.doPostDelver(token, newDelver)
}