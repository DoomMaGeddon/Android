package com.luismipalos.guideinabyss.views.layers.data.network

import android.util.Log
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.LayerDTO
import javax.inject.Inject

class LayersService @Inject constructor(private val client: LayersClient) {
    suspend fun doGetLayer(num: Int) : LayerDTO {
        val flora = client.doGetLayerFlora(num).body()
        val fauna = client.doGetLayerFauna(num).body()
        val layer = LayerDTO(fauna = fauna ?: emptyList(), flora = flora ?: emptyList())

        Log.i("Layer: ", layer.toString())
        return layer
    }

    suspend fun doPostFauna(token: String, newAnimal: FaunaFormularyDTO) : Boolean {
        Log.i("Animal post: ", newAnimal.toString())
        val res = client.doPostFauna(token, newAnimal).body()
        Log.i("Animal post: ", res.toString())
        return res?.status == "200"
    }

    suspend fun doPostFlora(token: String, newPlant: FloraFormularyDTO) : Boolean {
        val res = client.doPostFlora(token, newPlant).body()
        Log.i("Plant post: ", res.toString())
        return res?.status == "200"
    }
}