package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.repository.RecordListRepository

class PasswordHashUseCase(
    private val passwordListRepository: RecordListRepository
) {

    fun invoke(s:String) : String {
        return s;
    }
}