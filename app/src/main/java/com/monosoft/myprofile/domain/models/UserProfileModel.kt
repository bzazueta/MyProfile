package com.monosoft.myprofile.domain.models

import com.google.gson.annotations.SerializedName


data class UserProfileModel(

    @SerializedName("Respuesta")
    val respuesta: String,

    val id: Int,
    val name: String,
    val phone: String,

    @SerializedName("last_name")
    val lastName: String,

    val email: String,
    val image: String,

    @SerializedName("years_experience")
    val yearsExperience: Int,

    @SerializedName("contact_me")
    val contactMe: List<ContactMe>,

    @SerializedName("about_me")
    val aboutMe: String,

    @SerializedName("work_experience")
    val workExperience: List<WorkExperience>,

    @SerializedName("frameworks_and_tecnology")
    val frameworksAndTecnology: List<FrameworksAndTecnology>,

    @SerializedName("text2")
    val text2: String,

    @SerializedName("text3")
    val text3: String,

    val token: String
)


data class ContactMe (
    @SerializedName("id_contact_me")
    val idContactMe: Int,

    val tecnology: String,
    val image: String
)

data class WorkExperience (
    @SerializedName("Respuesta")
    val respuesta: String,
    val id: Int,
    val text1: String,
    val company: String,
    val years: String,
    val dates: String,
    val position: String,
    val image: String
)

data class FrameworksAndTecnology (
    val id: Long,
    @SerializedName("technology")
    val technology: String,
    val years: String,
    val image: String,
    val language: String,
    val text1: String?,
    val text2: String?,
    val text3: String?,
    val text4: String?,
    val text5: String?,
    val text6: String?,
    val text7: String?,

)

