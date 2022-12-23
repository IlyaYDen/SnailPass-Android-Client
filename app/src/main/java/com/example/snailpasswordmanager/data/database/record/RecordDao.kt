package com.example.snailpasswordmanager.data.database.record

import androidx.room.*
import com.example.snailpasswordmanager.data.model.RecordEntityDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao{
    @Query("SELECT * FROM passwords")
    fun getRecords() : Flow<List<RecordEntityDbModel>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun getRecordById(id: Int): RecordEntityDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(recordEntityDbModel: RecordEntityDbModel)

    @Delete
    suspend fun deleteRecord(recordEntityDbModel: RecordEntityDbModel)

}