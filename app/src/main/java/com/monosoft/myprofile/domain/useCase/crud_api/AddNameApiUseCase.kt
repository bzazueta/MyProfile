package com.monosoft.myprofile.domain.useCase.crud_api

import com.monosoft.myprofile.domain.repository.CrudApiRepository

class AddNameApiUseCase (private val crudApiRepository: CrudApiRepository){
    suspend operator fun invoke(name:String,image:String) = crudApiRepository.addName(name,image)
}