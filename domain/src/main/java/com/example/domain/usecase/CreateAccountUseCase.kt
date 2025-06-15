package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(private val accountServiceRepository: AccountServiceRepository) {
    suspend fun invoke(email: String, password: String, isSignUpSuccess: (Boolean) -> Unit) {
        accountServiceRepository.createAccount(email, password, isSignUpSuccess)
    }
}