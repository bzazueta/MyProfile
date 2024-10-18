package com.monosoft.myprofile.domain.models

import com.google.gson.annotations.SerializedName

data class WorkExperienceSkills (
    @SerializedName("Respuesta")
    val respuesta: String,

    val id: Int,
    val text1: String,
    val company: String,
    val years: String,
    val dates: String,
    val position: String,
    val image: String,
    val skills: List<Skills>
)

data class Skills (
    @SerializedName("Respuesta")
    val respuesta: String,
    val id: Int,
    @SerializedName("id_work_experience")
    val idWorkExperience: Int,
    val skill: String,
    val description: String,
    val image : String
)
