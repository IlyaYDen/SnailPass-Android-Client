package com.example.snailpasswordmanager.domain.usecase.Passwords

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository

class GetPasswordUseCase(private val passwordListRepository: PasswordListRepository) {

    fun execute(passwordId: Int) : PasswordEntity? {
        return passwordListRepository.getPassword(passwordId)
    }
}