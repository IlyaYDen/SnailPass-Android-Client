package com.example.snailpasswordmanager.presentation.accountInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.cryptography.Decode
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AccountInfoModelFactory @Inject constructor(
    var fieldUseCases: FieldUseCases,
    var passwordUseCases: PasswordUseCases,
    var decodeUseCase: Decode
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AccountInfoViewModel(
            passwordUseCases = passwordUseCases,
            fieldUseCases = fieldUseCases,
            decodeUseCase
        ) as T

    }
}