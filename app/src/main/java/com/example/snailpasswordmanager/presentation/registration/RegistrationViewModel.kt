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
import com.example.snailpasswordmanager.retrofit2.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.Response
import javax.inject.Inject
class RegistrationViewModel @Inject constructor(
    val userUseCases: UserUseCases
) : ViewModel() {

    val token = MutableStateFlow(Token("-"))

    @RequiresApi(Build.VERSION_CODES.O)
    fun registrationEvent(userEntity: UserEntity) {
        viewModelScope.launch {
            userUseCases.userRegisterUseCase(userEntity)
            token.value = userUseCases.userLoginUseCase(userEntity)
        }
    }
}
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