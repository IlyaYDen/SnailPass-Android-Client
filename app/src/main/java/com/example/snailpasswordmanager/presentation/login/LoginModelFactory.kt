package com.example.snailpasswordmanager.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import javax.inject.Inject

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