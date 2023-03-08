package com.example.snailpasswordmanager.presentation.recordList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class MainListViewModelFactory @Inject constructor(

    var passwordUseCases: PasswordUseCases,
    var fieldUseCases: FieldUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainListViewModel(
            passwordUseCases = passwordUseCases,
            fieldUseCases = fieldUseCases
        ) as T

    }
}