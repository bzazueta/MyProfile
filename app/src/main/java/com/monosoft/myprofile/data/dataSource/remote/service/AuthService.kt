package com.monosoft.myprofile.data.dataSource.remote.service



import com.monosoft.myprofile.domain.models.LoginModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthService {

    // http://192.168.1.15:3000/auth/login
    @FormUrlEncoded
    @POST("LoginResp.php")
    suspend fun login(
        @Field("usuario") usuario: String?,
        @Field("contraseña") contraseña: String?,
        @Field("marca_cel") marca_cel: String?,
        @Field("version") version: String?
    ): Response<LoginModel>



}