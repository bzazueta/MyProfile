package com.monosoft.myprofile.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CopyTextViewModel():ViewModel() {

    private val _textToCopy = MutableLiveData<String>()
    val textToCopy: LiveData<String> get() = _textToCopy

    fun setTextToCopy(text: String) {
        _textToCopy.value = text
    }
}