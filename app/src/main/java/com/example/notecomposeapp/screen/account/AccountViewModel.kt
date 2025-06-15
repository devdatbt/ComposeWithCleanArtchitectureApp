package com.example.notecomposeapp.screen.account

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.example.notecomposeapp.extension.isValidEmail
import com.example.notecomposeapp.usecase.AppUseCase
import com.example.notecomposeapp.screen.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val appUseCase: AppUseCase
) : BaseViewModel() {

    private val TAG = AccountViewModel::class.java.simpleName
    var uiState = mutableStateOf(AccountUiState())
        private set
    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    private val isAcceptPolicy
        get() = uiState.value.isAcceptPolicy

    private val confirmPassword
        get() = uiState.value.confirmPassword

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPassChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onConfirmPassChange(newValue: String) {
        uiState.value = uiState.value.copy(confirmPassword = newValue)
    }

    fun onSetIsAcceptPolicy(isAccept: Boolean){
        uiState.value = uiState.value.copy(isAcceptPolicy = isAccept)
    }

    fun cleanDataField() {
        uiState.value = uiState.value.copy(
            email = "",
            password = "",
            isAcceptPolicy = false,
            confirmPassword = ""
        )
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
            appUseCase.autheticateUseCase.invoke(email = email, pass = password) {
                isLoginSuccess.invoke(it)
            }
        }
    }

    fun createAccount(isSignUpSuccess: (Status) -> Unit) {
        if (!email.isValidEmail()) {
            Log.d(TAG, "isValidEmail")
            isSignUpSuccess.invoke(Status.INVALID_EMAIL)
            return
        }
        if (password.isBlank()) {
            Log.d(TAG, "isValidPassword")
            isSignUpSuccess.invoke(Status.INVALID_PASSWORD)
            return
        }
        if (!isAcceptPolicy) {
            Log.d(TAG, "Not Accept Policy")
            isSignUpSuccess.invoke(Status.NOT_AGREED_POLICY)
            return
        }
        if (password != confirmPassword) {
            Log.d(TAG, "Confirm password don't match")
            isSignUpSuccess.invoke(Status.INVALID_CONFIRM_PASSWORD)
            return
        }
        launchDataLoad {
            appUseCase.createAccountUseCase.invoke(email, password) {
                isSignUpSuccess.invoke(Status.SIGNUP_OK)
            }
        }
    }

    enum class Status(val status: String) {
        INVALID_EMAIL("Invalid email"),
        INVALID_PASSWORD("Invalid password"),
        NOT_AGREED_POLICY("Not agreed policy"),
        INVALID_CONFIRM_PASSWORD("Invalid confirm password"),
        SIGNUP_OK("Sign up ok");
    }
}