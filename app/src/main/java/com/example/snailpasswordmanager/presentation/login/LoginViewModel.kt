package com.example.snailpasswordmanager.presentation.login

import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        val logInUseCases: UserUseCases
        ) : ViewModel() {


        val boolean = MutableStateFlow(Pair("",LoginMode.ERROR))
        val sharedViewEffects = MutableSharedFlow<Pair<String,LoginMode>>() // 1
        @RequiresApi(Build.VERSION_CODES.O)
        fun logInEvent(entity: UserEntity) {


            viewModelScope.launch(Dispatchers.IO) {

                 val res = logInUseCases.userLoginUseCase(entity)
                 sharedViewEffects.emit(res)
            }
        }

}
