package com.monosoft.myprofile.data.dataSource.remote.service

import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.utils.Resource
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CrudApiService{
    @FormUrlEncoded
    @POST("addNameApi.php")
    suspend fun addNameApi(
        @Field("name") message: String?,
        @Field("image") image: String?,
        ): Response<CrudApiModel>

    @GET("getAllApi.php")
    suspend fun findAll(): Response<CrudApiModel>

    @FormUrlEncoded
    @POST("deleteNameApi.php")
    suspend fun deleteNameApi(
        @Field("id") id: String?,
    ): Response<SuccesModel>

    @FormUrlEncoded
    @POST("editNameApi.php")
    suspend fun editNameApi(
        @Field("id") id: String?,
        @Field("name") name: String?,
        @Field("image") image: String?,
    ): Response<SuccesModel>
}