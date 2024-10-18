package com.monosoft.myprofile.domain.models

import com.google.gson.annotations.SerializedName

data class ListSkillModel(
    @SerializedName("Respuesta")
    val respuesta: String,
    val id: Long,
    @SerializedName("id_work_experience")
    val idWorkExperience: Long,
    val skill: String,
    val description: String,
    val image : String
)