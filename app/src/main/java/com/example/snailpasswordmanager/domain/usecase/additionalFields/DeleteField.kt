package com.example.snailpasswordmanager.domain.usecase.additionalFields

import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class DeleteField @Inject constructor(
    private val additionalFieldsRepository: AdditionalFieldsRepository
) {
    suspend operator fun invoke(subList: MutableList<UUID>){
        additionalFieldsRepository.deleteFieldList(subList)
    }
}