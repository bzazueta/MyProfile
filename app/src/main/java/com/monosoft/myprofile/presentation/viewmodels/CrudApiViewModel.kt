package com.monosoft.myprofile.presentation.viewmodels

import android.icu.text.ListFormatter.Width
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.monosoft.myprofile.domain.models.CrudApiModel
import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.useCase.crud_api.AddNameApiUseCase
import com.monosoft.myprofile.domain.useCase.crud_api.CrudApiUseCase
import com.monosoft.myprofile.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrudApiViewModel @Inject constructor(
    private val apiUseCase: CrudApiUseCase
):ViewModel(){

    private var _addNameApiResponse = MutableStateFlow<Resource<CrudApiModel>?>(Resource.Loading)
    val addNameApiResponse : StateFlow<Resource<CrudApiModel>?> = _addNameApiResponse

    private var _getAllApiResponse = MutableStateFlow<Resource<CrudApiModel>?>(Resource.Loading)
    val  getAllApiResponse : StateFlow<Resource<CrudApiModel>?> = _getAllApiResponse

    private var _deleteApiResponse = MutableStateFlow<Resource<SuccesModel>?>(Resource.Loading)
    val  deleteApiResponse : StateFlow<Resource<SuccesModel>?> = _deleteApiResponse

    private var _editApiResponse = MutableStateFlow<Resource<SuccesModel>?>(Resource.Loading)
    val  editApiResponse : StateFlow<Resource<SuccesModel>?> = _editApiResponse

    fun getAllApi() = viewModelScope.launch {
        _getAllApiResponse.value = Resource.Loading
        apiUseCase.getAllApiUseCase.invoke().collect() {
            _getAllApiResponse.value=it
        }
    }

    fun addNameApi (name:String,image:String) = viewModelScope.launch {
        _addNameApiResponse.value = Resource.Loading
        val result = apiUseCase.addApiUseCase(name,image)
        _addNameApiResponse.value = result
    }

    fun deleteNameApi (id:String) = viewModelScope.launch {
        _deleteApiResponse.value = Resource.Loading
        val result = apiUseCase.deleteNameApiUseCase(id)
        _deleteApiResponse.value = result
        getAllApi()
    }

    fun editNameApi (id:String,name :String,image:String) = viewModelScope.launch {
        _editApiResponse.value = Resource.Loading
        val result = apiUseCase.editNameApiUseCase.editNameApi(id,name,image)
        _editApiResponse.value = result
        getAllApi()
    }
}