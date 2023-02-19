package com.example.snailpasswordmanager.domain.usecase.additionalFields

import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import java.util.*
import javax.inject.Inject

class CloneField @Inject constructor(
    private val additionalFieldsRepository: AdditionalFieldsRepository
) {
    suspend operator fun invoke(id: UUID){
        additionalFieldsRepository.cloneFieldById(id)
    }
}