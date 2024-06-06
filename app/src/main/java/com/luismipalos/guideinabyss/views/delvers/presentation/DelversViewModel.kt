package com.luismipalos.guideinabyss.views.delvers.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.delvers.data.network.dto.DelverDTO
import com.luismipalos.guideinabyss.views.delvers.domain.DelversUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DelversViewModel @Inject constructor(private val delversUseCase: DelversUseCase,
                                           private val loggedUserDao: LoggedUserDao) : ViewModel() {
    private val _delvers = MutableLiveData<List<DelverDTO>?>()
    val delvers: LiveData<List<DelverDTO>?> = _delvers

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _foto = MutableLiveData<String>()
    val foto: LiveData<String> = _foto

    private val _genero = MutableLiveData<String>()
    val genero: LiveData<String> = _genero

    private val _especie = MutableLiveData<String>()
    val especie: LiveData<String> = _especie

    private val _estado = MutableLiveData<String>()
    val estado: LiveData<String> = _estado

    private val _rango = MutableLiveData<String>()
    val rango: LiveData<String> = _rango

    private val _userRole = MutableLiveData<String?>()
    val userRole: LiveData<String?> = _userRole

    private val _successfulDelverPost = MutableLiveData<Boolean>()
    val successfulDelverPost: LiveData<Boolean> = _successfulDelverPost

    private val _failedDelverPost = MutableLiveData<Boolean>()
    val failedDelverPost: LiveData<Boolean> = _failedDelverPost

    private val _enabledSubmit = MutableLiveData<Boolean>()
    val enabledSubmit: LiveData<Boolean> = _enabledSubmit

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadDelvers() {
        _isLoading.value = true
        viewModelScope.launch {
            _userRole.value = loggedUserDao.getLoggedUser()!!.rol
            val response = delversUseCase()
            if (response !== null) {
                _delvers.value = response
            } else {
                _delvers.value = emptyList()
            }
        }

        _isLoading.value = false
    }

    fun onDelverFormularyChange(nombre: String, genero: String, especie: String, estado: String,
                                  rango: String)
    {
        val regex = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü'.,\\- ]+\$".toRegex()
        _nombre.value = nombre
        _genero.value = genero
        _especie.value = especie
        _estado.value = estado
        _rango.value = rango

        _enabledSubmit.value = regex.matches(nombre) &&
                (genero == "Masculino" || genero == "Femenino") &&
                regex.matches(especie) &&
                (estado == "Vivo" || estado == "Fallecido" || estado == "Viva"
                        || estado == "Fallecida") &&
                (rango == "Cascabel" || rango == "Silbato rojo" || rango == "Silbato azul"
                        || rango == "Silbato lunar" || rango == "Silbato negro"
                        || rango == "Silbato blanco")
    }

    fun onDelverFormularySubmit() {
        val rangoId: Int = when (_rango.value) {
            "Silbato rojo" -> 2
            "Silbato azul" -> 3
            "Silbato lunar" -> 4
            "Silbato negro" -> 5
            "Silbato blanco" -> 6
            else -> 1
        }

        viewModelScope.launch {
            val user = loggedUserDao.getLoggedUser()
            val newDelver = DelverDTO(
                _foto.value ?: "",
                true,
                user!!.email,
                _nombre.value!!,
                _genero.value!!,
                _especie.value!!,
                _estado.value!!,
                rangoId
            )

            val res = delversUseCase.invoke(user.token, newDelver)
            if (res) {
                _successfulDelverPost.value = true
            } else {
                _failedDelverPost.value = true
            }

            _foto.value = ""
            _nombre.value = ""
            _genero.value= ""
            _especie.value = ""
            _estado.value = ""
            _rango.value = ""
        }
    }

    fun dismissDialog() {
        _successfulDelverPost.value = false
        _failedDelverPost.value = false
    }
}