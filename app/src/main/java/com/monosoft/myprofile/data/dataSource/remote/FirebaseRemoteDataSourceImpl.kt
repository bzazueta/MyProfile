package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.data.dataSource.remote.service.FirebaseService
import com.monosoft.myprofile.data.dataSource.remote.service.NotificationService
import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.ResponseModel
import com.monosoft.myprofile.domain.models.SuccesModel
import retrofit2.Response

class FirebaseRemoteDataSourceImpl  (private val firebaseService: FirebaseService,private val notificationService: NotificationService) : FirebaseRemoteDataSource{
    override suspend fun sendNotificationPushFirebase(message: String, tknFrb: String, title: String):  Response<FirebaseNotificationModel>
    = notificationService.sendNotificationPushApi(message, tknFrb,title)//firebaseService.sendNotificationPush(message, tknFrb,title)//firebaseService.sendNotificationPush(message, tknFrb,title)

    override suspend fun uploadImageProfileFirebase(idUser: String, url: String): Response<ResponseModel> {
        return firebaseService.uploadImageProfileFirebase(idUser,url)
    }


}