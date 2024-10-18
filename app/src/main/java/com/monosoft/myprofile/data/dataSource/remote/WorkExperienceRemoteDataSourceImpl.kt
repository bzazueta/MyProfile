package com.monosoft.myprofile.data.dataSource.remote

import com.monosoft.myprofile.data.dataSource.remote.service.WorkExperienceService
import com.monosoft.myprofile.domain.models.WorkExperience
import com.monosoft.myprofile.domain.utils.Resource

class WorkExperienceRemoteDataSourceImpl (private val workExperienceService: WorkExperienceService): WorkExperienceRemoteDataSource {
    override suspend fun getWorkExperienceById(id: String) = workExperienceService.getWorkExperienceById(id)
}