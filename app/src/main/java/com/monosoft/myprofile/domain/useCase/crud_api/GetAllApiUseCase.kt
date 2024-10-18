package com.monosoft.myprofile.domain.useCase.crud_api

import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.repository.CrudApiRepository
import com.monosoft.myprofile.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetAllApiUseCase (private val crudApiRepository: CrudApiRepository) {

    suspend operator fun invoke()= crudApiRepository.getAllApi()
}