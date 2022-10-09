package com.example.snailpasswordmanager.presentation.login

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.util.PasswordOrder
import com.example.snailpasswordmanager.presentation.mainscreen.PasswordsEvent

sealed class LoginEvent {
    data class Login(val userEntity: UserEntity) : LoginEvent()
}