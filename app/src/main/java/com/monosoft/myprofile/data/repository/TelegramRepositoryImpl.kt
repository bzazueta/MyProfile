package com.monosoft.myprofile.data.repository

import com.monosoft.myprofile.data.dataSource.remote.TelegramRemoteDataSource
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.repository.TelegramRepository
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.domain.utils.ResponseToRequest

class TelegramRepositoryImpl (
    private val telegramRemoteDataSource: TelegramRemoteDataSource
): TelegramRepository{



    override suspend fun sendMessageTelegram(message: String): Resource<SuccesModel>  =
        ResponseToRequest.send(telegramRemoteDataSource.sendMessagetelegram(message))


}