package com.example.notecomposeapp.screen.account

data class AccountUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isAcceptPolicy: Boolean = false,
)