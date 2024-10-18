package com.monosoft.myprofile.domain.useCase

import com.monosoft.myprofile.domain.repository.AuthRepository

class LoginUseCase (private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password: String, marca: String, version: String) =
        authRepository.login(email,password,marca,version)
}