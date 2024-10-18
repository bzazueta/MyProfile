package com.monosoft.myprofile.domain.repository

import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CrudApiRepository {
    suspend fun addName(name : String,image : String) : Resource<CrudApiModel>
    fun getAllApi(): Flow<Resource<CrudApiModel>>
    suspend fun deleteName(id : String) : Resource<SuccesModel>
    suspend fun editName(id : String, name :String,image :String) : Resource<SuccesModel>
}