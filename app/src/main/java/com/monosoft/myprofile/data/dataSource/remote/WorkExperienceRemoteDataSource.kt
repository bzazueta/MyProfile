package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.domain.models.WorkExperience
import com.monosoft.myprofile.domain.models.WorkExperienceSkills
import com.monosoft.myprofile.domain.utils.Resource
import retrofit2.Response

interface WorkExperienceRemoteDataSource {

    suspend fun getWorkExperienceById(id :String):Response<WorkExperienceSkills>
}