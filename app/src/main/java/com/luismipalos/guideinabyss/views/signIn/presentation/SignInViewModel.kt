package com.luismipalos.guideinabyss.views.signIn.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.luismipalos.guideinabyss.core.navigation.AppScreens
import com.luismipalos.guideinabyss.core.roomDatabase.LoggedUserDao
import com.luismipalos.guideinabyss.views.signIn.domain.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val signInUseCase: SignInUseCase,
                                          private val loggedUserDao: LoggedUserDao) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _enableSignin = MutableLiveData<Boolean>()
    val enableSignin: LiveData<Boolean> = _enableSignin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _failedSignIn = MutableLiveData<Boolean>()
    val failedSignIn: LiveData<Boolean> = _failedSignIn

    fun onSigninChanged(email: String, pass: String) {
        _email.value = email
        _pass.value = pass
        _enableSignin.value = isValidEmail(email) && isValidPass(pass)
    }

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPass(pass: String): Boolean = pass.length >= 8

    suspend fun onSigninSelected(navController: NavController) {
        if (signInUseCase(_email.value!!, _pass.value!!, loggedUserDao)) {
            _isLoading.value = true
            delay(3000)
            navController.navigate(route = AppScreens.LayersScreen.route)
            _isLoading.value = false
        } else
            _failedSignIn.value = true
    }

    fun onSignupSelected(navController: NavController) {
        navController.navigate(route = AppScreens.SignUpScreen.route)
    }

    fun dismissErrorDialog() {
        _failedSignIn.value = false
    }
}