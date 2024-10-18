package com.monosoft.myprofile.domain.useCase.crud_api

import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.repository.CrudApiRepository
import com.monosoft.myprofile.domain.utils.Resource

class DeleteNameApiUseCase (private val crudApiRepository: CrudApiRepository) {

    suspend operator fun invoke(id : String):Resource<SuccesModel>
    = crudApiRepository.deleteName(id)
}