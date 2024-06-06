package com.luismipalos.guideinabyss.views.layers.data

import com.luismipalos.guideinabyss.views.layers.data.network.LayersService
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.LayerDTO
import javax.inject.Inject

class LayersRepository @Inject constructor(private val api: LayersService) {
    suspend fun getLayer(num: Int) : LayerDTO = api.doGetLayer(num)

    suspend fun postFauna(token: String, newAnimal: FaunaFormularyDTO) : Boolean =
        api.doPostFauna(token, newAnimal)

    suspend fun postFlora(token: String, newPlant: FloraFormularyDTO) : Boolean =
        api.doPostFlora(token, newPlant)
}