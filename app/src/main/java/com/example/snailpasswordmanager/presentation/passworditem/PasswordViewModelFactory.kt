package com.example.snailpasswordmanager.presentation.passworditem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PasswordViewModelFactory @Inject constructor(
    var passwordUseCases: PasswordUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PasswordViewModel(
            passwordUseCases = passwordUseCases
        ) as T

    }
}
