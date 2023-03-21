package com.example.snailpasswordmanager.data.database.record

import androidx.room.*
import com.example.snailpasswordmanager.data.model.RecordEntityDbModel
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface RecordDao{
    @Query("SELECT * FROM passwords")
    fun getRecords() : Flow<List<RecordEntityDbModel>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun getRecordById(id: Int): RecordEntityDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(recordEntityDbModel: RecordEntityDbModel)

    //@Delete
    @Query("DELETE FROM passwords WHERE id = :id")
    suspend fun deleteRecord(id : UUID)//(recordEntityDbModel: RecordEntityDbModel)

    //@Delete records
    @Query("DELETE FROM passwords")
    suspend fun deleteRecords()//(recordEntityDbModel: RecordEntityDbModel)
    @Query("DELETE FROM passwords WHERE user_id = :id")
    suspend fun deleteUserRecords(id : UUID)//(recordEntityDbModel: RecordEntityDbModel)

}