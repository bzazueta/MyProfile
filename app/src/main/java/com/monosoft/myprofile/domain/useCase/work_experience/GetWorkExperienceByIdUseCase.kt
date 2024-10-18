package com.monosoft.myprofile.domain.useCase.work_experience

import com.monosoft.myprofile.domain.repository.WorkExperienceRepository

class GetWorkExperienceByIdUseCase (
    private val workExperienceRepository: WorkExperienceRepository)
{
    suspend operator fun invoke(id: String)=
        workExperienceRepository.getWorkExperienceById(id)
}