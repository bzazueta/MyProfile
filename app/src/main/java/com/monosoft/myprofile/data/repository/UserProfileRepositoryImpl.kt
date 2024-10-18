package com.monosoft.myprofile.data.repository

import com.monosoft.ecommercebenja.data.dataSource.remote.AuthRemoteDataSource
import com.monosoft.myprofile.data.dataSource.remote.UserProfileDataSource
import com.monosoft.myprofile.domain.models.UserProfileModel
import com.monosoft.myprofile.domain.repository.ProfileRepository
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.domain.utils.ResponseToRequest

class UserProfileRepositoryImpl ( private val userProfileRemoteDataSource: UserProfileDataSource)
    : ProfileRepository {

    override suspend fun getDataUserProfile(): Resource<UserProfileModel> = ResponseToRequest.send(
        userProfileRemoteDataSource.getUserProfile()
    )

}