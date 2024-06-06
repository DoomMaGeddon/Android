package com.luismipalos.guideinabyss.views.signUp.presentation

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.luismipalos.guideinabyss.views.signUp.domain.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _enableSignup = MutableLiveData<Boolean>()
    val enableSignup: LiveData<Boolean> = _enableSignup

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _failedSignUp = MutableLiveData<Boolean>()
    val failedSignUp: LiveData<Boolean> = _failedSignUp

    private val _successfulSignUp = MutableLiveData<Boolean>()
    val successfulSignUp: LiveData<Boolean> = _successfulSignUp

    fun onSignupChanged(username: String, email: String, pass: String) {
        _username.value = username
        _email.value = email
        _pass.value = pass
        _enableSignup.value = isValidUsername(username) && isValidEmail(email) && isValidPass(pass)
    }

    private fun isValidUsername(name: String): Boolean =
        "^[A-Z][a-zA-Z]+(?: [A-Z][a-zA-Z]*){0,2}\$".toRegex().containsMatchIn(name)

    private fun isValidEmail(email: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches()

    private fun isValidPass(pass: String): Boolean = pass.length >= 8

    suspend fun onSignupSelected() {
        if (signUpUseCase(_username.value!!, _email.value!!, _pass.value!!)) {
            _successfulSignUp.value = true
        } else
            _failedSignUp.value = true
    }

    fun onSigninSelected(navController: NavController) {
        navController.popBackStack()
    }

    fun dismissErrorDialog() {
        _failedSignUp.value = false
    }

    fun dismissSuccessDialog(navController: NavController) {
        _isLoading.value = true
        navController.popBackStack()
        _successfulSignUp.value = false
        _isLoading.value = false
    }
}