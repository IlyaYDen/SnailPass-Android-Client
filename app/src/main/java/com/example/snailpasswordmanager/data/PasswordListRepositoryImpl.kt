package com.example.snailpasswordmanager.data

import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.domain.repository.PasswordListRepository
import java.lang.RuntimeException

object PasswordListRepositoryImpl : PasswordListRepository {

    private val passwordList = mutableListOf<PasswordEntity>()

    private var autoIncrementid = 0;

    init {
        for(i in 0 until 100){
            val item = PasswordEntity("Service: $i","test","test",i)
            addPassword(item)
        }
    }

    override fun addPassword(passwordEntity: PasswordEntity) {
        if(passwordEntity.id == PasswordEntity.UNDEFINED_ID)
            passwordEntity.id = autoIncrementid++
        passwordList.add(passwordEntity)
        //TODO("Not yet implemented")
    }

    override fun removePassword(passwordEntity: PasswordEntity) {
        passwordList.remove(passwordEntity)
        //TODO("Not yet implemented")
    }

    override fun getPasswordList(): List<PasswordEntity> {
        return passwordList.toList()
    //TODO("Not yet implemented")
    }

    override fun getPassword(passwordId: Int): PasswordEntity? {
        return passwordList.find { it.id == passwordId };
        //TODO("Not yet implemented")
    }
}