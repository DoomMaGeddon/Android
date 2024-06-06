package com.luismipalos.guideinabyss.views.artifacts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.artifacts.data.network.dto.ArtifactDTO
import com.luismipalos.guideinabyss.views.artifacts.domain.ArtifactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtifactsViewModel @Inject constructor(private val artifactsUseCase: ArtifactsUseCase,
                                             private val loggedUserDao: LoggedUserDao) : ViewModel() {
    private val _artifacts = MutableLiveData<List<ArtifactDTO>?>()
    val artifacts: LiveData<List<ArtifactDTO>?> = _artifacts

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _foto = MutableLiveData<String>()
    val foto: LiveData<String> = _foto

    private val _grado = MutableLiveData<String>()
    val grado: LiveData<String> = _grado

    private val _efecto = MutableLiveData<String>()
    val efecto: LiveData<String> = _efecto

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _origen = MutableLiveData<String>()
    val origen: LiveData<String> = _origen

    private val _userRole = MutableLiveData<String?>()
    val userRole: LiveData<String?> = _userRole

    private val _successfulArtifactPost = MutableLiveData<Boolean>()
    val successfulArtifactPost: LiveData<Boolean> = _successfulArtifactPost

    private val _failedArtifactPost = MutableLiveData<Boolean>()
    val failedArtifactPost: LiveData<Boolean> = _failedArtifactPost

    private val _enabledSubmit = MutableLiveData<Boolean>()
    val enabledSubmit: LiveData<Boolean> = _enabledSubmit

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun loadArtifacts() {
        _isLoading.value = true
        viewModelScope.launch {
            val response: List<ArtifactDTO>? = artifactsUseCase()
            if (response != null) {
                _artifacts.value = response
            } else {
                _artifacts.value = emptyList()
            }
            _userRole.value = loggedUserDao.getLoggedUser()!!.rol
        }
        _isLoading.value = false
    }

    fun onArtifactFormularyChange(nombre: String, grado: String, efecto: String, origen: String,
                               descripcion: String)
    {
        val regex = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü'.,\\- ]+\$".toRegex()
        _nombre.value = nombre
        _grado.value = grado
        _efecto.value = efecto
        _origen.value = origen
        _descripcion.value = descripcion

        _enabledSubmit.value = regex.matches(nombre) &&
                (grado == "Primer" || grado == "Segundo" || grado == "Tercer" || grado == "Cuarto"
                        || grado == "Especial" || grado == "Aubade") &&
                regex.matches(efecto) &&
                regex.matches(origen) &&
                regex.matches(descripcion)
    }

    fun onArtifactFormularySubmit() {
        viewModelScope.launch {
            val user = loggedUserDao.getLoggedUser()
            val newArtifact = ArtifactDTO(
                _nombre.value!!,
                _foto.value ?: "",
                _grado.value!!,
                _efecto.value!!,
                _descripcion.value!!,
                _origen.value!!,
                null,
                true,
                user!!.email
            )

            val res = artifactsUseCase.invoke(user.token, newArtifact)
            if (res) {
                _successfulArtifactPost.value = true
            } else {
                _failedArtifactPost.value = true
            }

            _nombre.value = ""
            _foto.value = ""
            _grado.value= ""
            _efecto.value = ""
            _descripcion.value = ""
            _origen.value = ""
        }
    }

    fun dismissDialog() {
        _successfulArtifactPost.value = false
        _failedArtifactPost.value = false
    }
}