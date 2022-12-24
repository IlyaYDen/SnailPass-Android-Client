package com.example.snailpasswordmanager.presentation.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.retrofit2.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        val logInUseCases: UserUseCases
        ) : ViewModel() {

        val token = MutableStateFlow(Token("-"))

        @RequiresApi(Build.VERSION_CODES.O)
        fun logInEvent(entity: UserEntity) {
                viewModelScope.launch {
                        token.value = logInUseCases.userLoginUseCase.invoke(entity)
                }
        }
        fun passwordHash(password: String) : String {
                return password
        }



}


@Suppress("UNCHECKED_CAST")
class LoginModelFactory @Inject constructor(
        var logInUseCases: UserUseCases
) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(
                        logInUseCases
                ) as T

        }
}