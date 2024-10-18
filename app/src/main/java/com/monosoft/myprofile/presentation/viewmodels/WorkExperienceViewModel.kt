package com.monosoft.myprofile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.models.WorkExperience
import com.monosoft.myprofile.domain.models.WorkExperienceSkills
import com.monosoft.myprofile.domain.useCase.work_experience.WorkExperienceUseCase
import com.monosoft.myprofile.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkExperienceViewModel @Inject constructor (
    private val workExperienceUseCase: WorkExperienceUseCase)
    : ViewModel() {

    private var _workExperienceResponse = MutableStateFlow<Resource<WorkExperienceSkills>?>(Resource.Loading)
    val workExperienceResponse : StateFlow<Resource<WorkExperienceSkills>?> = _workExperienceResponse

    fun getWorkExperienceById(id : String) = viewModelScope.launch{
        _workExperienceResponse.value = Resource.Loading
        val result = workExperienceUseCase.getWorkExperienceByIdUseCase(id)
        _workExperienceResponse.value = result
    }

}