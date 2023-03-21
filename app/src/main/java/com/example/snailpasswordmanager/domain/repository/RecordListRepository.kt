package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.RecordEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

interface RecordListRepository {

    suspend fun getRecordList(): Flow<List<RecordEntity>?>

    suspend fun getRecordById(id: Int): RecordEntity?

    suspend fun insertRecord(passwordEntity: RecordEntity)

    suspend fun editRecord(passwordEntity: RecordEntity)

    suspend fun deleteRecord(id: UUID)

}