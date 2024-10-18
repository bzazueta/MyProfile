package com.monosoft.myprofile.domain.models

import com.google.gson.annotations.SerializedName

data class SuccesModel(
    val message : String,
    val response : String,
    @SerializedName("Msg")
    val msg :String
)
