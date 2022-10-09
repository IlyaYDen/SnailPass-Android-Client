package com.example.snailpasswordmanager.domain.usecase.user

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import javax.inject.Inject

data class UserUseCases @Inject constructor(
    val userLoginUseCase: UserLoginUseCase
)

    //fun execute(passwordEntity: PasswordEntity){
