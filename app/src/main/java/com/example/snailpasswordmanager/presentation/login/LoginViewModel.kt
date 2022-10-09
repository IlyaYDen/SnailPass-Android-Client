package com.example.snailpasswordmanager.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.usecase.passwords.InsertPassword
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        val logInUseCases: UserUseCases
        ) : ViewModel() {


        fun onEvent(loginEvent: LoginEvent) : Boolean {
                when (loginEvent){
                        is LoginEvent.Login -> {
                                viewModelScope.launch {
                                        logInUseCases.userLoginUseCase(loginEvent.userEntity)
                                }
                        }
                }
                return false
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