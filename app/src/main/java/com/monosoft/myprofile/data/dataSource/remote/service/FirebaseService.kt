package com.monosoft.myprofile.data.dataSource.remote.service

import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.ResponseModel
import com.monosoft.myprofile.domain.models.SuccesModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FirebaseService {
    @FormUrlEncoded
    @POST("apiFireBaseNotificactionPush.php")
    suspend fun sendNotificationPush(
        @Field("message") message: String?,
        @Field("firebase_token") tknFrb: String?,
        @Field("title") title: String?,

    ): Response<FirebaseNotificationModel>

    @FormUrlEncoded
    @POST("saveUrlImageFirebase.php")
    suspend fun uploadImageProfileFirebase(
        @Field("id_user") idUser: String?,
        @Field("url") url: String?,


        ): Response<ResponseModel>
}