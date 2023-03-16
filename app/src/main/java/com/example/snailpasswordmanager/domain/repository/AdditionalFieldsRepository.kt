package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface AdditionalFieldsRepository {

    suspend fun getField(id: UUID): Flow<List<RecordAddFieldEntity>?>
//todo i need two ones?
    suspend fun cloneFieldById(id: UUID)
    suspend fun clearFieldTable()

    suspend fun insertField(addFieldEntity: RecordAddFieldEntity)

    suspend fun editField(addFieldEntity: RecordAddFieldEntity)

    suspend fun editFieldList(addFieldEntityList: List<RecordAddFieldEntity>)

    suspend fun deleteFieldList(addFieldEntityList: List<UUID>)
    suspend fun deleteLocalFieldByRecordId(id: UUID)

}