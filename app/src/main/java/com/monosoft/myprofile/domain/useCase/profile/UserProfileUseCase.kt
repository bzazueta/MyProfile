package com.monosoft.myprofile.domain.useCase.profile

import com.monosoft.myprofile.domain.repository.AuthRepository
import com.monosoft.myprofile.domain.repository.ProfileRepository

class UserProfileUseCase (private val profile : ProfileRepository){
    suspend operator fun invoke() =
        profile.getDataUserProfile()
}