package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.ResponseModel
import com.monosoft.myprofile.domain.models.SuccesModel
import retrofit2.Response

interface FirebaseRemoteDataSource {

    suspend fun sendNotificationPushFirebase(message: String,tknFrb: String,title :String): Response<FirebaseNotificationModel>
    suspend fun uploadImageProfileFirebase(idUser: String,url: String): Response<ResponseModel>

}