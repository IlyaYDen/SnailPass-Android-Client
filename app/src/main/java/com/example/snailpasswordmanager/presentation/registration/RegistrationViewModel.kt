package com.example.snailpasswordmanager.presentation.registration

import androidx.lifecycle.ViewModel
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.usecase.user.UserRegisterUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import okhttp3.Response
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    val userUseCases: UserUseCases
) : ViewModel() {
    fun onEvent(loginEvent: RegistrationEvent) {

    }

    suspend fun registrationEvent(userEntity: UserEntity) {
        userUseCases.userRegisterUseCase(userEntity)
        return
    }
}