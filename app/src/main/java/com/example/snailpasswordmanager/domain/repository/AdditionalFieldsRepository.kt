package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface AdditionalFieldsRepository {

    suspend fun getField(id: UUID): Flow<List<RecordAddFieldEntity>?>

    suspend fun cloneFieldById(id: UUID)

    suspend fun insertField(addFieldEntity: RecordAddFieldEntity)

    suspend fun editField(addFieldEntity: RecordAddFieldEntity)

    suspend fun editFieldList(addFieldEntityList: List<RecordAddFieldEntity>)

    //suspend fun deleteRecord(passwordEntity: RecordEntity)

}