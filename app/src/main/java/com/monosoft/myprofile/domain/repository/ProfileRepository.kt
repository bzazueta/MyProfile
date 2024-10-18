package com.monosoft.myprofile.domain.repository

import com.monosoft.myprofile.domain.models.UserProfileModel
import com.monosoft.myprofile.domain.utils.Resource

interface ProfileRepository {
    suspend fun getDataUserProfile() : Resource<UserProfileModel>
}