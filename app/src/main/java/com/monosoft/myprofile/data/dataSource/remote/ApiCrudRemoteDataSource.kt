package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.models.SuccesModel
import retrofit2.Response

interface ApiCrudRemoteDataSource {
    suspend fun getAllApi(): Response<CrudApiModel>
    suspend fun addNameApi(name :String,image :String): Response<CrudApiModel>
    suspend fun deleteNameApi(id :String): Response<SuccesModel>
    suspend fun editNameApi(id: String,name:String,image:String): Response<SuccesModel>
}