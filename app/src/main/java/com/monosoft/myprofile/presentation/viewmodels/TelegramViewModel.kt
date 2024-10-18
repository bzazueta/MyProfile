package com.monosoft.myprofile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.models.UserProfileModel
import com.monosoft.myprofile.domain.useCase.telegram.SendMessageTelegramUseCase
import com.monosoft.myprofile.domain.useCase.telegram.TelegramUseCase
import com.monosoft.myprofile.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TelegramViewModel  @Inject constructor(
  private val telegramUseCase: TelegramUseCase
):ViewModel(){

    private var _telegramResponse = MutableStateFlow<Resource<SuccesModel>?>(Resource.Loading)
    val telegramResponse : StateFlow<Resource<SuccesModel>?> = _telegramResponse


    fun sendMessageTelegram(message :String)= viewModelScope.launch {
        val s= ""
        _telegramResponse.value = Resource.Loading
        val result =telegramUseCase.sendMessageTelegramUseCase(message)
        _telegramResponse.value = result

    }
}