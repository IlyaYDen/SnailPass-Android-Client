package com.example.snailpasswordmanager.data.database.password

import androidx.room.*
import com.example.snailpasswordmanager.data.model.PasswordEntityDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PassowrdDao{
    @Query("SELECT * FROM passwords")
    fun getPasswords() : Flow<List<PasswordEntityDbModel>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun getPasswordById(id: Int): PasswordEntityDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(passwordEntityDbModel: PasswordEntityDbModel)

    @Delete
    suspend fun deletePassword(passwordEntityDbModel: PasswordEntityDbModel)

}