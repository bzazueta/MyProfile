package com.monosoft.myprofile.domain.repository

import com.monosoft.myprofile.domain.models.WorkExperience
import com.monosoft.myprofile.domain.models.WorkExperienceSkills
import com.monosoft.myprofile.domain.utils.Resource
import retrofit2.Response

interface WorkExperienceRepository {

    suspend fun getWorkExperienceById(id :String): Resource<WorkExperienceSkills>
}