package com.luismipalos.guideinabyss.views.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.luismipalos.guideinabyss.core.navigation.AppScreens
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.profile.data.network.dto.EntryHistoryDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.FormularyDTO
import com.luismipalos.guideinabyss.views.profile.data.network.dto.ProfileDTO
import com.luismipalos.guideinabyss.views.profile.domain.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val profileUseCase: ProfileUseCase,
                                           private val loggedUserDao: LoggedUserDao
) : ViewModel() {
    private val _nombreUsuario = MutableLiveData<String>()
    val nombreUsuario: LiveData<String> = _nombreUsuario

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _fotoPerfil = MutableLiveData<String?>()
    val fotoPerfil: LiveData<String?> = _fotoPerfil

    private val _contrasenya = MutableLiveData("")
    val contrasenya: LiveData<String> = _contrasenya

    private val _descripcion = MutableLiveData<String?>()
    val descripcion: LiveData<String?> = _descripcion

    private val _experiencia = MutableLiveData<Int>()
    val experiencia: LiveData<Int> = _experiencia

    private val _rango = MutableLiveData<Int?>()
    val rango: LiveData<Int?> = _rango

    private val _notificaciones = MutableLiveData(true)
    val notificaciones: LiveData<Boolean> = _notificaciones

    private val _rol = MutableLiveData<String?>()
    val rol: LiveData<String?> = _rol

    private val _descripcionFormulario = MutableLiveData<String?>()
    val descripcionFormulario: LiveData<String?> = _descripcionFormulario

    private val _historialEntradas = MutableLiveData<EntryHistoryDTO>()
    val historialEntradas: LiveData<EntryHistoryDTO> = _historialEntradas

    private val _enableUpdate = MutableLiveData<Boolean>()
    val enableUpdate: LiveData<Boolean> = _enableUpdate

    private val _successfulUpdate = MutableLiveData<Boolean>()
    val successfulUpdate: LiveData<Boolean> = _successfulUpdate

    private val _successfulFormularySubmit = MutableLiveData<Boolean>()
    val successfulFormularySubmit: LiveData<Boolean> = _successfulFormularySubmit

    private val _failedUpdate = MutableLiveData<Boolean>()
    val failedUpdate: LiveData<Boolean> = _failedUpdate

    private val _failedLogOut = MutableLiveData<Boolean>()
    val failedLogOut: LiveData<Boolean> = _failedLogOut

    suspend fun onLogOutSelected(navController: NavController) {
        if (profileUseCase(loggedUserDao.getLoggedUser()!!.token)) {
            loggedUserDao.deleteLoggedUser()
            navController.navigate(route = AppScreens.SignInScreen.route)
        } else
            _failedLogOut.value = true
    }

    suspend fun loadUserData() {
        _historialEntradas.value = profileUseCase.invoke(loggedUserDao.getLoggedUser()!!.token, "")
        val user = loggedUserDao.getLoggedUser()
        _nombreUsuario.value = user!!.nombreUsuario
        _email.value = user.email
        _fotoPerfil.value = user.fotoPerfil
        _descripcion.value = user.descripcion
        _experiencia.value = user.experiencia
        _rango.value = user.rango
        _rol.value = user.rol
    }

    private fun isValidPass(pass: String): Boolean = pass.length >= 8 || pass.isEmpty()

    private fun isValidUsername(name: String): Boolean =
        "^[A-Z][a-zA-Z]+(?: [A-Z][a-zA-Z]*){0,2}\$".toRegex().containsMatchIn(name)

    fun onUpdateChanged(username: String, fotoPerfil: String, pass: String, descripcion: String,
                        notif: Boolean) {
        _nombreUsuario.value = username
        _fotoPerfil.value = fotoPerfil
        _contrasenya.value = pass
        _descripcion.value = descripcion
        _notificaciones.value = notif
        _enableUpdate.value = isValidPass(pass) && isValidUsername(username)
    }

    fun onFormularyChange(desc: String) {
        _descripcionFormulario.value = desc
    }

    fun onNotificationsChanged(changed: Boolean) {
        _notificaciones.value = changed
    }

    suspend fun onUpdateSelected() {
        val loggedUser = loggedUserDao.getLoggedUser()
        val user = ProfileDTO(
            loggedUser!!.email,
            _nombreUsuario.value ?: "",
            _contrasenya.value,
            _fotoPerfil.value,
            _descripcion.value,
            _notificaciones.value ?: true
        )

        if (profileUseCase.invoke(loggedUser.token, user)) {
            _successfulUpdate.value = true
            _contrasenya.value = ""
        }
    }

    suspend fun onFormularySubmit() {
        val loggedUser = loggedUserDao.getLoggedUser()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val roleRequest = FormularyDTO(
            loggedUser!!.email,
            dateFormat.format(Date()),
            _descripcionFormulario.value
        )

        if (profileUseCase.invoke(loggedUser.token, roleRequest)) {
            _successfulFormularySubmit.value = true
        }
    }

    fun dismissDialog() {
        _failedUpdate.value = false
        _failedLogOut.value = false
        _successfulUpdate.value = false
        _successfulFormularySubmit.value = false
    }
}