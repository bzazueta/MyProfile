package com.monosoft.myprofile.domain.repository

import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.ResponseModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.utils.Resource

interface FirebaseRepository {

    suspend fun sendNotificationPushFirebase(message : String,tknFrb : String,title :String): Resource<FirebaseNotificationModel>

    suspend fun uploadImageProfileFirebase(idUser : String,urlImage : String): Resource<ResponseModel>

}