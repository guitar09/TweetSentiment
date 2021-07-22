package com.higor.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    fun callSuspend(function: suspend  () -> Unit){
        viewModelScope.launch {
            function()
        }
    }
}