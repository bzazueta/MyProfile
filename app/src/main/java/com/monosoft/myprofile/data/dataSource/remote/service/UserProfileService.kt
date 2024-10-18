package com.monosoft.myprofile.data.dataSource.remote.service

import com.monosoft.myprofile.domain.models.LoginModel
import com.monosoft.myprofile.domain.models.UserProfileModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserProfileService {

    //@FormUrlEncoded
    @POST("userProfile.php")
    suspend fun getUserprofile(
    ): Response<UserProfileModel>
}