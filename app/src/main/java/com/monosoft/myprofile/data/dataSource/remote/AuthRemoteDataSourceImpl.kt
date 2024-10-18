package com.monosoft.ecommercebenja.data.dataSource.remote


import com.monosoft.myprofile.data.dataSource.remote.service.AuthService
import com.monosoft.myprofile.domain.models.LoginModel
import retrofit2.Response

/**
 * pasamos por constructor nuestro service private val authService: AuthService
 * este  authService: AuthService nos lo provee la carpeta di que es donde declaramos
 * nuestras inyeccions de dependencias
 */
class AuthRemoteDataSourceImpl(private val authService: AuthService): AuthRemoteDataSource {

    override suspend fun login(email: String, password: String,marca: String,version: String)
    = authService.login(email, password,marca,version)

}