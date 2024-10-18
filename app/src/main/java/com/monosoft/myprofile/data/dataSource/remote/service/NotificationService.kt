package com.monosoft.myprofile.data.dataSource.remote.service

import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NotificationService {
    @FormUrlEncoded
    @POST("getTokenFirebase.php")
    suspend fun sendNotificationPushApi(
        @Field("body") message: String?,
        @Field("deviceToken") tknFrb: String?,
        @Field("title") title: String?,

        ): Response<FirebaseNotificationModel>
}