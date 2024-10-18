package com.monosoft.myprofile.data.repository

import com.monosoft.myprofile.data.dataSource.remote.FirebaseRemoteDataSource
import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.ResponseModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.repository.FirebaseRepository
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.domain.utils.ResponseToRequest

class FirebaseRepositoryImpl(
    private val firebaseRemoteDataSource: FirebaseRemoteDataSource
   )
    : FirebaseRepository
{
    override suspend fun sendNotificationPushFirebase(
        message: String,
        tknFrb: String,
        title :String
    ):Resource<FirebaseNotificationModel>  =
        ResponseToRequest.send( firebaseRemoteDataSource.sendNotificationPushFirebase(message,tknFrb,title))

    override suspend fun uploadImageProfileFirebase(
        idUser: String,
        urlImage: String
    ): Resource<ResponseModel> {
        return ResponseToRequest.send( firebaseRemoteDataSource.uploadImageProfileFirebase(idUser,urlImage))
    }


}