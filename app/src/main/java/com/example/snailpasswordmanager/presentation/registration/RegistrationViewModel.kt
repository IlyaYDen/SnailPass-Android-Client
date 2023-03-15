package com.example.snailpasswordmanager.presentation.registration

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.usecase.user.UserRegisterUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.presentation.login.LoginViewModel
import com.example.snailpasswordmanager.data.retrofit2.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    val userUseCases: UserUseCases
) : ViewModel() {

    val boolean = MutableStateFlow(Pair(false,""))

    @RequiresApi(Build.VERSION_CODES.O)
    fun registrationEvent(userEntity: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {

            val a = userUseCases.userRegisterUseCase(userEntity)
            if(a.second)
                boolean.value = Pair(true,"")
            else
                boolean.value = Pair(false,a.first)


        }
    }
}
