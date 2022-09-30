package com.example.snailpasswordmanager.domain.repository

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import kotlinx.coroutines.flow.Flow

interface PasswordListRepository {

    fun getPasswordList(): Flow<List<PasswordEntity>>

    suspend fun getPasswordById(id: Int): PasswordEntity?

    suspend fun insertPassword(passwordEntity: PasswordEntity)

    suspend fun deletePassword(passwordEntity: PasswordEntity)

}