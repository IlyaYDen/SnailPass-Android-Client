package com.example.snailpasswordmanager.data.database.record

import androidx.room.*
import com.example.snailpasswordmanager.data.model.RecordAddFieldEntityDbModel
import com.example.snailpasswordmanager.data.model.UserEntityDbModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface RecordAddFieldDao {
    @Query("SELECT * FROM additional_fields")
    fun getFields() : Flow<List<RecordAddFieldEntityDbModel>>


    @Query("SELECT * FROM additional_fields WHERE record_id=:id")
    fun getFieldsByRecord(id:String) : Flow<List<RecordAddFieldEntityDbModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addField(recordAddFieldEntityDbModel: RecordAddFieldEntityDbModel)

    @Query("DELETE FROM additional_fields WHERE record_id=:id")
    fun deleteFieldById(id: String)

    @Query("DELETE FROM additional_fields")
    suspend fun deleteFields()//(recordEntityDbModel: RecordEntityDbModel)

}