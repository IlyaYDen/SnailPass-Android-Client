package com.example.snailpasswordmanager.presentation.login

import com.example.snailpasswordmanager.domain.model.UserEntity

sealed class LoginEvent {
    data class Login(val userEntity: UserEntity) : LoginEvent()
}