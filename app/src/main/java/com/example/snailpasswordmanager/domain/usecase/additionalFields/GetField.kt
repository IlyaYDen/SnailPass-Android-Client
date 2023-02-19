package com.example.snailpasswordmanager.domain.usecase.additionalFields

import com.example.snailpasswordmanager.data.model.RecordAddFieldEntityDbModel
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class GetField @Inject constructor(
    private val additionalFieldsRepository: AdditionalFieldsRepository
) {
    suspend operator fun invoke(id: UUID): Flow<List<RecordAddFieldEntity>?> {
        return additionalFieldsRepository.getField(id)
    }
}