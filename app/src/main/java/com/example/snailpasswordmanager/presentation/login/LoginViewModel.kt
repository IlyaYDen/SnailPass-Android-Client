package com.example.snailpasswordmanager.presentation.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        val logInUseCases: UserUseCases
        ) : ViewModel() {


        //val auth = MutableStateFlow(Pair("-","-"))
        var boolean = MutableStateFlow(false)

        @RequiresApi(Build.VERSION_CODES.O)
        fun logInEvent(entity: UserEntity) {
                        viewModelScope.launch(Dispatchers.IO) {
                                boolean.value = logInUseCases.userLoginUseCase(entity)
                        }
        }
        fun passwordHash(password: String) : String {
                return password
        }
}
