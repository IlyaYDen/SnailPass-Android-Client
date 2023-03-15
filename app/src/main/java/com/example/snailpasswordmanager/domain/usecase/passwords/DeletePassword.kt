package com.example.snailpasswordmanager.domain.usecase.passwords

import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import java.util.*
import javax.inject.Inject

class DeletePassword @Inject constructor(
    private val passwordListRepository: RecordListRepository
     ) {
    suspend operator fun invoke(id: UUID){
        passwordListRepository.deleteRecord(id) //todo переделать удаление
    }
}






















