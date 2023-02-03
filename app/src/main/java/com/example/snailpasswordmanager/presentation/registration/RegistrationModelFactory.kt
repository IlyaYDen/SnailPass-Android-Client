package com.example.snailpasswordmanager.presentation.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RegistrationModelFactory @Inject constructor(
    var userUseCases: UserUseCases
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegistrationViewModel(
            userUseCases
        ) as T
    }
}