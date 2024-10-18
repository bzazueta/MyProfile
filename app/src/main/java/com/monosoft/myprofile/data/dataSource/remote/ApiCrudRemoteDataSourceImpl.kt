package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.data.dataSource.remote.service.CrudApiService
import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.models.SuccesModel
import retrofit2.Response

class ApiCrudRemoteDataSourceImpl (private val crudApiService: CrudApiService) : ApiCrudRemoteDataSource {

    override suspend fun getAllApi(): Response<CrudApiModel> =crudApiService.findAll()

    override suspend fun addNameApi(name: String,image:String): Response<CrudApiModel> =
        crudApiService.addNameApi(name,image)

    override suspend fun deleteNameApi(id: String): Response<SuccesModel> = crudApiService.deleteNameApi(id)

    override suspend fun editNameApi(id: String,name:String,image:String): Response<SuccesModel> = crudApiService.editNameApi(id,name,image)


}