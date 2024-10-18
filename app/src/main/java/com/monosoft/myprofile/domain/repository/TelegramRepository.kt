package com.monosoft.myprofile.domain.repository

import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.models.UserProfileModel
import com.monosoft.myprofile.domain.utils.Resource

interface TelegramRepository {

    suspend fun sendMessageTelegram(message: String) : Resource<SuccesModel>
}