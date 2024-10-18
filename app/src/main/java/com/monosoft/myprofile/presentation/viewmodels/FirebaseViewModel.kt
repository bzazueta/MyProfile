package com.monosoft.myprofile.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.ResponseModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.useCase.firebase.FirebaseUseCase
import com.monosoft.myprofile.domain.useCase.firebase.SendNotificationPushFirebaseUseCase
import com.monosoft.myprofile.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val firebaseUseCase: FirebaseUseCase
): ViewModel() {


    private var _firebaseResponse = MutableStateFlow<Resource<FirebaseNotificationModel>?>(Resource.Loading)
    val firebaseResponse : StateFlow<Resource<FirebaseNotificationModel>?> = _firebaseResponse

    private var _firebaseResponseImage = MutableStateFlow<Resource<ResponseModel>?>(Resource.Loading)
    val firebaseResponseImage : StateFlow<Resource<ResponseModel>?> = _firebaseResponseImage

    fun sendNotificationPushFirebase(message : String, tknFrb : String,title : String)= viewModelScope.launch {
        _firebaseResponse.value = Resource.Loading
         val result = firebaseUseCase.sendNotificationPushFirebaseUseCase(message, tknFrb,title)
        _firebaseResponse.value = result
    }

    fun loading()= viewModelScope.launch{
        _firebaseResponse.value = Resource.Loading
    }

    fun uploadImageProfile(idUser:String,url:String)= viewModelScope.launch {
        _firebaseResponseImage.value = Resource.Loading
        val result = firebaseUseCase.uploadImageProfileFirebaseUseCase(idUser,url)
        _firebaseResponseImage.value = result
    }
}