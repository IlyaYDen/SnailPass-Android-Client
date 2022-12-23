package com.example.snailpasswordmanager.domain.usecase.user

import javax.inject.Inject

data class UserUseCases @Inject constructor(
    val userLoginUseCase: UserLoginUseCase,
    val userRegisterUseCase: UserRegisterUseCase
)

    //fun execute(passwordEntity: PasswordEntity){
