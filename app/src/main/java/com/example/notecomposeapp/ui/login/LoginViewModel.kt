package com.example.notecomposeapp.ui.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.notecomposeapp.ext.isValidEmail
import com.example.notecomposeapp.usecase.AppUseCase
import com.example.notecomposeapp.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appUseCase: AppUseCase
) : BaseViewModel() {

    private val TAG = LoginViewModel::class.java.simpleName
    var uiState = mutableStateOf(LoginUiState())
        private set
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPassChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun authenticationEmail(isLoginSuccess: (Boolean) -> Unit) {
        if (!email.isValidEmail()) {
            Log.d(TAG, "isValidEmail")
            isLoginSuccess.invoke(false)
            return
        }
        if (password.isBlank()) {
            Log.d(TAG, "isValidPassword")
            isLoginSuccess.invoke(false)
            return
        }
        launchDataLoad {
            appUseCase.autheticateUseCase.invoke(email = email, pass = password)
            isLoginSuccess.invoke(true)
        }
    }
}