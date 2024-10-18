package com.monosoft.myprofile.data.dataSource.remote.service

import com.monosoft.myprofile.domain.models.LoginModel
import com.monosoft.myprofile.domain.models.SuccesModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TelegramService {
    @FormUrlEncoded
    @POST("sendMessageTelegram.php")
    suspend fun sendMessage(
        @Field("message") message: String?,
    ): Response<SuccesModel>
}