package com.example.snailpasswordmanager.presentation.login

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.snailpasswordmanager.domain.usecase.passwords.InsertPassword
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
        val logInUseCases: UserUseCases
        ) : ViewModel() {


        @RequiresApi(Build.VERSION_CODES.O)
        fun onEvent(loginEvent: LoginEvent) : String? {
                return when (loginEvent){
                        is LoginEvent.Login -> {
                                var g : String? = null
                                var f = viewModelScope.launch {
                                        g = logInUseCases.userLoginUseCase.invoke(
                                                loginEvent.userEntity
                                        )
                                }
                                g
                        }
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