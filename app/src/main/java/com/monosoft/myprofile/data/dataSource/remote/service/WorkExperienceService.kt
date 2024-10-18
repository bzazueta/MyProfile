package com.monosoft.myprofile.data.dataSource.remote.service


import com.monosoft.myprofile.domain.models.WorkExperienceSkills
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface WorkExperienceService {
    @FormUrlEncoded
    @POST("workExperienceById.php")
    suspend fun getWorkExperienceById(
        @Field("id") id: String?,
    ): Response<WorkExperienceSkills>
}