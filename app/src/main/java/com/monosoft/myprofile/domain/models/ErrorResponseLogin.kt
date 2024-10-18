package com.monosoft.myprofile.domain.models

data class ErrorResponseLogin (
val result: List<ResultErrorLogin>
)

data class ResultErrorLogin (
    val respuesta: String,
    val msg: String,
    val type: String,
    val idUsuario: String
)

