package com.example.snailpasswordmanager.presentation.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.services.ConnectionCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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
                val connection = ConnectionCheck().invoke()
               if (connection) {
                 val res = logInUseCases.userLoginUseCase(entity)
                 //boolean.emit(res)
                 sharedViewEffects.emit(res)
               }
               else{

                   val res = logInUseCases.userLoginOfflineUseCase(entity)
                   sharedViewEffects.emit(Pair("", LoginMode.OFFLINE))
               }
            }
        }

}
