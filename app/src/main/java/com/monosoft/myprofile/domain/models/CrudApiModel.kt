package com.monosoft.myprofile.domain.models

import com.google.gson.annotations.SerializedName

data class CrudApiModel(
    @SerializedName("Respuesta")
    val respuesta: String,
    @SerializedName("api_crud")
    val apiCRUD: List<CrudApiList>
)

data class CrudApiList(
    @SerializedName("Respuesta")
    val respuesta: String,
    val id: Int,
    val  name : String,
    val image :String
)