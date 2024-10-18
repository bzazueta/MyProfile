package com.monosoft.myprofile.domain.useCase

import com.monosoft.myprofile.domain.repository.AuthRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

data class AuthUseCase (
    val login: LoginUseCase,
    //val saveTkn : SaveTokenUseCase
)