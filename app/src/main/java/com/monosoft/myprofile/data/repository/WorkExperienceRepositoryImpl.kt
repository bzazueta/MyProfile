package com.monosoft.myprofile.data.repository

import com.monosoft.myprofile.data.dataSource.remote.WorkExperienceRemoteDataSource
import com.monosoft.myprofile.domain.models.WorkExperience
import com.monosoft.myprofile.domain.models.WorkExperienceSkills
import com.monosoft.myprofile.domain.repository.WorkExperienceRepository
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.domain.utils.ResponseToRequest
import retrofit2.Response

class WorkExperienceRepositoryImpl (private val workExperienceRemoteDataSource: WorkExperienceRemoteDataSource) : WorkExperienceRepository{
    override suspend fun getWorkExperienceById(id: String): Resource<WorkExperienceSkills> =
        ResponseToRequest.send(workExperienceRemoteDataSource.getWorkExperienceById(id))
}