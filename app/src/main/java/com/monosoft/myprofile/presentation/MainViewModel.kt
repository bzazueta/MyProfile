package com.monosoft.myprofile.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import com.monosoft.myprofile.domain.models.LoginModel
import com.monosoft.myprofile.domain.models.UserProfileModel
import com.monosoft.myprofile.domain.useCase.AuthUseCase
import com.monosoft.myprofile.domain.useCase.profile.ProfileUseCase
import com.monosoft.myprofile.domain.useCase.profile.UserProfileUseCase
import com.monosoft.myprofile.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel  @Inject constructor(
    private val authUseCase: AuthUseCase,
    private  val  profileUseCase: ProfileUseCase
   ): ViewModel() {

    var loginResponse_ by mutableStateOf<Resource<LoginModel>?>(null)
        private set

    private var _loginResponse = MutableStateFlow<Resource<LoginModel>?>(Resource.Loading)
    val loginResponse: StateFlow<Resource<LoginModel>?> = _loginResponse

    private var _userResponse = MutableStateFlow<Resource<UserProfileModel>?>(Resource.Loading)
    val userResponse: StateFlow<Resource<UserProfileModel>?> = _userResponse

    fun login() = viewModelScope.launch {
        _userResponse.value = Resource.Loading
        val result = profileUseCase.userProfileUseCase()
        _userResponse.value = result

    }



}