package com.luismipalos.guideinabyss.views.delvers.domain

import com.luismipalos.guideinabyss.views.delvers.data.DelversRepository
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import javax.inject.Inject

class DelversUseCase @Inject constructor(
    private val networkRepository: DelversRepository
) {
    suspend operator fun invoke() : List<DelverDTO>? = networkRepository.getDelvers()

    suspend operator fun invoke(token: String, newDelver: DelverDTO) : Boolean =
        networkRepository.postDelver(token, newDelver)
}