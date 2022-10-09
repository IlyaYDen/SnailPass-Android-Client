package com.example.snailpasswordmanager.data.repository

import android.util.Log
import com.example.snailpasswordmanager.data.database.password.PassowrdDao
import com.example.snailpasswordmanager.data.model.PasswordEntityMapper
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class PasswordListRepositoryImpl @Inject constructor(
    private val dao: PassowrdDao
    ) : PasswordListRepository {

    private val mapper = PasswordEntityMapper()

    override fun getPasswordList(): Flow<List<PasswordEntity>> {
        return dao.getPasswords().map {
            mapper.mapListDbModelToListEntity(it)
        }
    }

    override suspend fun getPasswordById(id: Int): PasswordEntity? {
        return dao.getPasswordById(id)?.let { mapper.mapDbModelToEntity(it) }
    }

    override suspend fun insertPassword(passwordEntity: PasswordEntity) {
        dao.insertPassword(mapper.mapEntityToDbModel(passwordEntity))
    }

    override suspend fun deletePassword(passwordEntity: PasswordEntity) {
        dao.deletePassword(mapper.mapEntityToDbModel(passwordEntity))
    }


}