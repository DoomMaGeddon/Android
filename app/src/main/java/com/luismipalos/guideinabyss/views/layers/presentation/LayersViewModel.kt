package com.luismipalos.guideinabyss.views.layers.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FaunaFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.FloraFormularyDTO
import com.luismipalos.guideinabyss.views.layers.data.network.dto.LayerDTO
import com.luismipalos.guideinabyss.views.layers.domain.LayersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LayersViewModel @Inject constructor(private val layersUseCase: LayersUseCase,
                                          private val loggedUserDao: LoggedUserDao) : ViewModel()
{
    private val _fauna = MutableLiveData<List<FaunaDTO>?>()
    val fauna: LiveData<List<FaunaDTO>?> = _fauna

    private val _flora = MutableLiveData<List<FloraDTO>?>()
    val flora: LiveData<List<FloraDTO>?> = _flora

    private val _userRole = MutableLiveData<String?>()
    val userRole: LiveData<String?> = _userRole

    private val _userRank = MutableLiveData<Int?>()
    val userRank: LiveData<Int?> = _userRank

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _foto = MutableLiveData<String>()
    val foto: LiveData<String> = _foto

    private val _especie = MutableLiveData<String>()
    val especie: LiveData<String> = _especie

    private val _peligro = MutableLiveData<String>()
    val peligro: LiveData<String> = _peligro

    private val _dieta = MutableLiveData<String>()
    val dieta: LiveData<String> = _dieta

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _enabledSubmit = MutableLiveData<Boolean>()
    val enabledSubmit: LiveData<Boolean> = _enabledSubmit

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _successfulAnimalPost = MutableLiveData<Boolean>()
    val successfulAnimalPost: LiveData<Boolean> = _successfulAnimalPost

    private val _successfulPlantPost = MutableLiveData<Boolean>()
    val successfulPlantPost: LiveData<Boolean> = _successfulPlantPost

    private val _failedAnimalPost = MutableLiveData<Boolean>()
    val failedAnimalPost: LiveData<Boolean> = _failedAnimalPost

    private val _failedPlantPost = MutableLiveData<Boolean>()
    val failedPlantPost: LiveData<Boolean> = _failedPlantPost

    fun onFaunaFormularyChange(nombre: String, especie: String, peligro: String, dieta: String,
                          descripcion: String)
    {
        val regex = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü'\\- ]+\$".toRegex()
        _nombre.value = nombre
        _especie.value = especie
        _peligro.value = peligro
        _dieta.value = dieta
        _descripcion.value = descripcion

        _enabledSubmit.value = regex.matches(nombre) &&
                regex.matches(especie) &&
                regex.matches(peligro) &&
                regex.matches(dieta) &&
                regex.matches(descripcion)
    }

    fun onFloraFormularyChange(nombre: String, especie: String, descripcion: String)
    {
        _nombre.value = nombre
        _especie.value = especie
        _descripcion.value = descripcion

        val regex = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü'.,\\- ]+\$".toRegex()

        _enabledSubmit.value = regex.matches(nombre) &&
                regex.matches(especie) &&
                regex.matches(descripcion)
    }

    fun onLayerSelected(num: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val layer: LayerDTO = layersUseCase.invoke(num)
            _fauna.value = layer.fauna
            _flora.value = layer.flora
            val user = loggedUserDao.getLoggedUser()
            _userRank.value = user!!.rango
            _userRole.value = user.rol
        }
        _isLoading.value = false
    }

    fun onFaunaFormularySubmit(numLayer: Int) {
        viewModelScope.launch {
            val user = loggedUserDao.getLoggedUser()
            val newAnimal = FaunaFormularyDTO(
                _foto.value ?: "",
                true,
                user!!.email,
                _nombre.value!!,
                _especie.value!!,
                numLayer,
                _peligro.value!!,
                _dieta.value!!,
                _descripcion.value!!,
            )

            val res = layersUseCase.invoke(user.token, newAnimal)
            if (res) {
                _successfulAnimalPost.value = true
            } else {
                _failedAnimalPost.value = true
            }

            _foto.value = ""
            _nombre.value = ""
            _especie.value = ""
            _dieta.value = ""
            _peligro.value = ""
            _descripcion.value = ""
        }
    }

    fun onFloraFormularySubmit(numLayer: Int) {
        viewModelScope.launch {
            val user = loggedUserDao.getLoggedUser()
            val newPlant = FloraFormularyDTO(
                _foto.value ?: "",
                true,
                user!!.email,
                _nombre.value!!,
                _especie.value!!,
                numLayer,
                _descripcion.value!!,
            )

            val res = layersUseCase.invoke(user.token, newPlant)
            if (res) {
                _successfulPlantPost.value = true
            } else {
                _failedPlantPost.value = true
            }

            _foto.value = ""
            _nombre.value = ""
            _especie.value = ""
            _descripcion.value = ""
        }
    }

    fun dismissDialog() {
        _successfulAnimalPost.value = false
        _successfulPlantPost.value = false
        _failedAnimalPost.value = false
        _failedPlantPost.value = false
    }
}