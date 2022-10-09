package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.repository.PasswordListRepository

class PasswordHashUseCase(
    private val passwordListRepository: PasswordListRepository
) {

    fun invoke(s:String) : String {
        return s;
    }
}