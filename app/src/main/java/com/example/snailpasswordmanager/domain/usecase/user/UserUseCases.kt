package com.example.snailpasswordmanager.domain.usecase.user

import javax.inject.Inject

data class UserUseCases @Inject constructor(
    val userLoginUseCase: UserLoginUseCase,
    val userRegisterUseCase: UserRegisterUseCase,
    val userLoginOfflineUseCase: UserLoginOfflineUseCase
)

//fun execute(passwordEntity: PasswordEntity){
