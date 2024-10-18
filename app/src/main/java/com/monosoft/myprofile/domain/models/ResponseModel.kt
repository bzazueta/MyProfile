package com.monosoft.myprofile.domain.models

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("Respuesta")
    val respuesta: String,
    @SerializedName("Msg")
    val msg: String,
    val code: Long,
    @SerializedName("last_id")
    val lastID: Long,
    val error: String
)
