package com.example.pussies.base.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pussies.base.presentation.model.Navigation

abstract class BaseViewModel : ViewModel() {
    val navigate = MutableLiveData<Navigation>()
}