package com.example.snailpasswordmanager.domain.usecase.Passwords

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository

class GetPasswordListUseCase(private val passwordListRepository: PasswordListRepository) {

    fun execute() : List<PasswordEntity> {
        return passwordListRepository.getPasswordList();
    }
}