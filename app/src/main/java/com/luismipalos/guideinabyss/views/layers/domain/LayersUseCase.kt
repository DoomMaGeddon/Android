package com.luismipalos.guideinabyss.views.layers.domain

import com.luismipalos.guideinabyss.views.layers.data.LayersRepository
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.LayerDTO
import javax.inject.Inject

class LayersUseCase @Inject constructor(
    private val networkRepository: LayersRepository
) {
    suspend operator fun invoke(num: Int) : LayerDTO = networkRepository.getLayer(num)

    suspend operator fun invoke(token: String, newAnimal: FaunaFormularyDTO) : Boolean =
        networkRepository.postFauna(token, newAnimal)

    suspend operator fun invoke(token: String, newPlant: FloraFormularyDTO) : Boolean =
        networkRepository.postFlora(token, newPlant)
}