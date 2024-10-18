package com.monosoft.myprofile.data.repository

import com.monosoft.myprofile.data.dataSource.remote.ApiCrudRemoteDataSource
import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.repository.CrudApiRepository
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.domain.utils.ResponseToRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApiCrudRepositoryImpl  (private val crudRemoteDataSource: ApiCrudRemoteDataSource) : CrudApiRepository{

    override suspend fun addName(name: String, image: String): Resource<CrudApiModel> =
        ResponseToRequest.send(crudRemoteDataSource.addNameApi(name,image))

    override fun getAllApi(): Flow<Resource<CrudApiModel>> = flow {
        ResponseToRequest.send(crudRemoteDataSource.getAllApi()).run {
            when(this){
                is Resource.Success->{
                    val result = this.data
                    emit(Resource.Success(this.data))
                }
                is Resource.Failure->{

                }
                else -> {

                }
            }
         }

    }.flowOn(Dispatchers.IO)

    override suspend fun deleteName(id: String): Resource<SuccesModel> =
        ResponseToRequest.send(crudRemoteDataSource.deleteNameApi(id))

    override suspend fun editName(id: String, name: String, image: String): Resource<SuccesModel> =
        ResponseToRequest.send(crudRemoteDataSource.editNameApi(id,name,image))

}