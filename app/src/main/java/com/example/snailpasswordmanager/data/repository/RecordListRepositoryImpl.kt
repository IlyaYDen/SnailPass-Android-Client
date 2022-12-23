package com.example.snailpasswordmanager.data.repository

import com.example.snailpasswordmanager.data.database.record.RecordDao
import com.example.snailpasswordmanager.data.model.RecordEntityMapper
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RecordListRepositoryImpl @Inject constructor(
    private val dao: RecordDao
    ) : RecordListRepository {

    private val mapper = RecordEntityMapper()

    override fun getRecordList(): Flow<List<RecordEntity>> {
        return dao.getRecords().map {
            mapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun getRecordById(id: Int): RecordEntity? {
        return dao.getRecordById(id)?.let { mapper.mapDbModelToEntity(it) }
    }

    override suspend fun insertRecord(passwordEntity: RecordEntity) {
        dao.insertRecord(mapper.mapEntityToDbModel(passwordEntity))
    }

    override suspend fun deleteRecord(passwordEntity: RecordEntity) {
        dao.deleteRecord(mapper.mapEntityToDbModel(passwordEntity))
    }


}