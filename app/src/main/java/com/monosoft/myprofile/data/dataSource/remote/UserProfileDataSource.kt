package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.domain.models.LoginModel
import com.monosoft.myprofile.domain.models.UserProfileModel
import retrofit2.Response

interface UserProfileDataSource {
    suspend fun getUserProfile(): Response<UserProfileModel>

}