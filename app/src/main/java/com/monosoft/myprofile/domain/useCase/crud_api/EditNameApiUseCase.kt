package com.monosoft.myprofile.domain.useCase.crud_api

import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.repository.CrudApiRepository
import com.monosoft.myprofile.domain.utils.Resource

class EditNameApiUseCase (private val crudApiRepository: CrudApiRepository) {

    suspend fun editNameApi(id:String, name :String, image :String):Resource<SuccesModel> =
        crudApiRepository.editName(id,name,image)
}