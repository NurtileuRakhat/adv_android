package com.example.bottomnav.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData(0)
    val text: LiveData<Int> = _text


    internal fun increase() {
        _text.value = _text.value?.plus(1)
    }
}