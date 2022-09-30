package com.example.snailpasswordmanager.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val resultLiveData = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveData


}