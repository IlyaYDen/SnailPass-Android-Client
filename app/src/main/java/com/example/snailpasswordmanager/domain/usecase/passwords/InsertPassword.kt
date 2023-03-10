

package com.example.snailpasswordmanager.domain.usecase.passwords

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import java.security.SecureRandom
import java.util.*
import javax.inject.Inject

class InsertPassword @Inject constructor(
    private val recordListRepository: RecordListRepository,
    private val userEntityAuth: UserEntity
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(passwordEntity: RecordEntity) {
        if (passwordEntity.name.isBlank()) throw InvalidRecordException("The service can't be empty.") //todo make validation for it
        if (passwordEntity.login.isBlank()) throw InvalidRecordException("The login can't be empty.") //todo make validation for it
        if (passwordEntity.encrypted_password.isBlank()) throw InvalidRecordException("The password can't be empty.") //todo make validation for it

        val masterpass = Base64.getDecoder().decode(userEntityAuth.password)

        val (name, nameNonce) = encryptData(passwordEntity.name.toByteArray(), masterpass)
        val (login, loginNonce) = encryptData(passwordEntity.login.toByteArray(), masterpass)
        val (encryptedPassword, encryptedPasswordNonce) = encryptData(passwordEntity.encrypted_password.toByteArray(), masterpass)

        recordListRepository.insertRecord(RecordEntity(
            id = passwordEntity.id,
            name = encodeData(name, nameNonce),
            login = encodeData(login, loginNonce),
            encrypted_password = encodeData(encryptedPassword, encryptedPasswordNonce),
            editedTime = passwordEntity.editedTime,
            creationTime = passwordEntity.creationTime,
            userId = userEntityAuth.id.toString(),
            isfavorite = passwordEntity.isfavorite
        ))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun encryptData(data: ByteArray, key: ByteArray): Pair<ByteArray, ByteArray> {
        val nonce = generateNonce()
        val encryptedData = AESUtil.encrypt(data, key, nonce)
        return encryptedData to nonce
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun encodeData(data: ByteArray, nonce: ByteArray): String {
        return "${Base64.getEncoder().encodeToString(data)}:${Base64.getEncoder().encodeToString(nonce)}"
    }
/*
    private fun generateNonce(): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789+=-"
        return (0..15)
            .map { charset.random() }
            .joinToString("")ddf@fff.fff
    }*/
    fun generateNonce(): ByteArray {
        val random = SecureRandom()
        val nonce = ByteArray(16)
        random.nextBytes(nonce)
        return nonce
    }
}


/*package com.example.snailpasswordmanager.domain.usecase.passwords

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.RecordListRepository
import java.util.*


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



       // val masterpass = userEntityAuth.password.toByteArray()//
        val masterpass = Base64.getDecoder().decode(userEntityAuth.password.toByteArray())

        val loginNonce = nonceGen()

        //val login = AESUtil.encrypt(passwordEntity.login.toByteArray(), masterpass, loginNonce.toByteArray()) // 32 length Key authInfo.hash2
        val login = AESUtil.encrypt(passwordEntity.login.toByteArray(), masterpass, loginNonce.toByteArray()) // 32 length Key authInfo.hash2

        val nameNonce = nonceGen()
        //val name = AESUtil.encrypt(passwordEntity.name.toByteArray(), masterpass, nameNonce.toByteArray()) // 32 length Key
        val name = AESUtil.encrypt(passwordEntity.name.toByteArray(), masterpass, nameNonce.toByteArray()) // 32 length Key

        val encrypted_passwordNonce = nonceGen()
        //val encrypted_password = AESUtil.encrypt(passwordEntity.encrypted_password.toByteArray(), masterpass, encrypted_passwordNonce.toByteArray()) // 32 length Key
        val encrypted_password = AESUtil.encrypt(passwordEntity.encrypted_password.toByteArray(), masterpass, encrypted_passwordNonce.toByteArray()) // 32 length Key

        //-Log.d("MYLOG_testE",passwordEntity.id.toString())
        recordListRepository.insertRecord(RecordEntity(
            id = passwordEntity.id,
            name = String(Base64.getEncoder().encode(name)).replace("\n","")                                + ":" + String(Base64.getEncoder().encode(nameNonce.toByteArray())) ,//todo
            login = String(Base64.getEncoder().encode(login)).replace("\n","")                              + ":" + String(Base64.getEncoder().encode(loginNonce.toByteArray()))  ,//todo
            //nonce = t,
            encrypted_password = String(Base64.getEncoder().encode(encrypted_password)).replace("\n","")    + ":" + String(Base64.getEncoder().encode(encrypted_passwordNonce.toByteArray()))  ,//todo
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
*/