package com.example.snailpasswordmanager.domain.usecase.passwords

import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository


import javax.inject.Inject

class InsertPassword @Inject constructor(
    private val recordListRepository: RecordListRepository,
    private val userEntityAuth: UserEntity
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(passwordEntity: RecordEntity){
        if(passwordEntity.name.isBlank())
            throw InvalidRecordException("The service can't be empty.")
        if(passwordEntity.login.isBlank())
            throw InvalidRecordException("The login can't be empty.")
        if(passwordEntity.encrypted_password.isBlank())
            throw InvalidRecordException("The password can't be empty.")



        val masterpass = Base64.decode(userEntityAuth.password.toByteArray(),0)

        val loginNonce = nonceGen()
        val login = AESUtil.encrypt(passwordEntity.login.toByteArray(), masterpass, loginNonce.toByteArray()) // 32 length Key authInfo.hash2

        val nameNonce = nonceGen()
        val name = AESUtil.encrypt(passwordEntity.name.toByteArray(), masterpass, nameNonce.toByteArray()) // 32 length Key

        val encrypted_passwordNonce = nonceGen()
        val encrypted_password = AESUtil.encrypt(passwordEntity.encrypted_password.toByteArray(), masterpass, encrypted_passwordNonce.toByteArray()) // 32 length Key

        Log.d("MYLOG_testE",passwordEntity.id.toString())

        recordListRepository.insertRecord(RecordEntity(
            id = passwordEntity.id,
            name = String(name).replace("\n","")                                + ":" + nameNonce,//todo
            login = String(login).replace("\n","")                              + ":" + loginNonce,//todo
            //nonce = t,
            encrypted_password = String(encrypted_password).replace("\n","")    + ":" + encrypted_passwordNonce,//todo
            editedTime = passwordEntity.editedTime,
            creationTime = passwordEntity.creationTime,
            userId = userEntityAuth.id.toString(),
            isfavorite = passwordEntity.isfavorite
        ))

    }
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789+=-"
    fun nonceGen() = (0..15)
            .map { charset.random() }
            .joinToString("")
}
