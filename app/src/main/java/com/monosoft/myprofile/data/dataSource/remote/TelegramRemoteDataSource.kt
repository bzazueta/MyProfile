package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.models.UserProfileModel
import retrofit2.Response

interface TelegramRemoteDataSource {

    suspend fun sendMessagetelegram(message: String): Response<SuccesModel>
}