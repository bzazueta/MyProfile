package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.data.dataSource.remote.service.AuthService
import com.monosoft.myprofile.data.dataSource.remote.service.TelegramService
import com.monosoft.myprofile.domain.models.SuccesModel
import retrofit2.Response

class TelegramRemoteDataSourceImpl (private val telegramService: TelegramService) : TelegramRemoteDataSource{




    override suspend fun sendMessagetelegram(message: String): Response<SuccesModel>  =  telegramService.sendMessage(message)


}