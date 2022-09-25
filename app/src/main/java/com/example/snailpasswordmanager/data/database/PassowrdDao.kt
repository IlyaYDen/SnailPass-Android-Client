package com.example.snailpasswordmanager.data.database

import androidx.room.*
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PassowrdDao {
    @Query("SELECT * FROM passwords")
    fun getPasswords() : Flow<List<PasswordEntity>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun getPasswordById(id: Int): PasswordEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPassword(passwordEntity: PasswordEntity)

    @Delete
    suspend fun deletePassword(passwordEntity: PasswordEntity)

}