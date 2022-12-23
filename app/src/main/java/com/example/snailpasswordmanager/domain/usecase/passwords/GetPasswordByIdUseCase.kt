package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository

class GetPasswordByIdUseCase(private val passwordListRepository: RecordListRepository) {

    suspend fun invoke(passwordId: Int) : RecordEntity? {
        return passwordListRepository.getRecordById(passwordId)
    }
}