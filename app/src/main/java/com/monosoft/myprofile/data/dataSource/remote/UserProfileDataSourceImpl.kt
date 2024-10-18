package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.data.dataSource.remote.service.UserProfileService

class UserProfileDataSourceImpl (private val userProfileService: UserProfileService) : UserProfileDataSource {

    override suspend fun getUserProfile()=userProfileService.getUserprofile()

}