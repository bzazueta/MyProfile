package com.monosoft.myprofile.data.repository


import com.monosoft.ecommercebenja.data.dataSource.remote.AuthRemoteDataSource
import com.monosoft.myprofile.domain.models.LoginModel
import com.monosoft.myprofile.domain.repository.AuthRepository
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.domain.utils.ResponseToRequest
import kotlinx.coroutines.flow.Flow

/**
 * pasamos por constructor nuestro service private val authRemoteDataSource: AuthRemoteDataSource
 * este  authRemoteDataSource: AuthRemoteDataSource nos lo provee la carpeta di que es donde declaramos
 * nuestras inyecciones de dependencias. ademas nos provee los datos del servidor
 */
class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,

    ): AuthRepository {

    override suspend fun login(email: String, password: String, marca: String, version: String): Resource<LoginModel> = ResponseToRequest.send(
        authRemoteDataSource.login(email, password,marca,version)
    )




}