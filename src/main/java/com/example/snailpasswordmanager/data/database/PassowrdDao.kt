package com.example.snailpasswordmanager.data.database

import androidx.room.*
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Dao
interface PassowrdDao{
    @Query("SELECT * FROM passwords")
    fun getPasswords() : Flow<List<PasswordEntity>>

    @Query("SELECT * FROM passwords WHERE id = :id")
    suspend fun getPasswordById(id: Int): PasswordEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(passwordEntity: PasswordEntity)

    @Delete
    suspend fun deletePassword(passwordEntity: PasswordEntity)

}