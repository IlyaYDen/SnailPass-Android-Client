package com.example.snailpasswordmanager.domain.usecase.additionalFields

import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import java.util.*
import javax.inject.Inject

class DeleteFieldsTable @Inject constructor(
    private val additionalFieldsRepository: AdditionalFieldsRepository
) {
    suspend operator fun invoke(){
        additionalFieldsRepository.clearFieldTable()
    }
}