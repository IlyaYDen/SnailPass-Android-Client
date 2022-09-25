package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository

class GetPasswordByIdUseCase(private val passwordListRepository: PasswordListRepository) {

    suspend fun invoke(passwordId: Int) : PasswordEntity? {
        return passwordListRepository.getPasswordById(passwordId)
    }
}