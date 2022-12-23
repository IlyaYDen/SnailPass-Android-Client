package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.RecordEntity
import kotlinx.coroutines.flow.Flow

interface RecordListRepository {

    fun getRecordList(): Flow<List<RecordEntity>>

    suspend fun getRecordById(id: Int): RecordEntity?

    suspend fun insertRecord(passwordEntity: RecordEntity)

    suspend fun deleteRecord(passwordEntity: RecordEntity)

}