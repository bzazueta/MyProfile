package com.monosoft.myprofile.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monosoft.myprofile.domain.useCase.network_checked.NetworkCheckedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkCheckedViewModel @Inject constructor(
    private val networkCheckedUseCase: NetworkCheckedUseCase
): ViewModel()
{


    val networkCheck = MutableLiveData<Boolean>(false)
    val networkConnected = MutableLiveData<Boolean>(false)
    val isLoading = MutableLiveData<Boolean>(true)

    fun networkChecked(context: Context) {
        viewModelScope.launch {
            val result = networkCheckedUseCase.execute()
            if (result)
            {
                isLoading.value = false
            }
            networkCheck.value = result
        }
    }

//    fun exitInternet(context: Context) {
//        viewModelScope.launch {
//            val result = networkCheckedUseCase.execute2()
//            if (result)
//            {
//                isLoading.value = false
//            }else{
//                isLoading.value = false
//            }
//            networkConnected.value = result
//        }
//    }
}