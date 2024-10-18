package com.monosoft.myprofile.domain.useCase.crud_api

data class CrudApiUseCase(
    val addApiUseCase : AddNameApiUseCase,
    val getAllApiUseCase: GetAllApiUseCase,
    val deleteNameApiUseCase: DeleteNameApiUseCase,
    val editNameApiUseCase: EditNameApiUseCase
)
