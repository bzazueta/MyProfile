package com.monosoft.ecommercebenja.data.dataSource.remote



import com.monosoft.myprofile.domain.models.LoginModel
import retrofit2.Response

/**
 * esta clase nos trae la informacion de una api de un servidor
 */
interface AuthRemoteDataSource {

    suspend fun login(email: String, password: String,marca: String,version: String): Response<LoginModel>

}