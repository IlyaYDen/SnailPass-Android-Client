package com.example.snailpasswordmanager.data.repository

import android.content.Context
import com.example.snailpasswordmanager.data.database.PassowrdDao
import com.example.snailpasswordmanager.data.database.PasswordsDb
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PasswordListRepositoryImpl (
    private val dao: PassowrdDao
    ) : PasswordListRepository {


   //init {
   //    for(i in 0 until 100){
   //        val item = PasswordEntity(
   //            service = "service $i",
   //            password = "password $i",
   //            login = "login $i"
   //        )
   //        //insertPassword(item)
   //    }
   //}

    override fun getPasswordList(): Flow<List<PasswordEntity>> {
        return dao.getPasswords()
    }

    override suspend fun getPasswordById(id: Int): PasswordEntity? {
        return dao.getPasswordById(id)
    }

    override suspend fun insertPassword(passwordEntity: PasswordEntity) {
        dao.insertPassword(passwordEntity)
    }

    override suspend fun deletePassword(passwordEntity: PasswordEntity) {
        dao.deletePassword(passwordEntity)
    }


}