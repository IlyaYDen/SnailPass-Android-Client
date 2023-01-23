package com.example.snailpasswordmanager.domain.usecase.passwords

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

        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789+=-"
        val t =
            (0..15)
                .map { charset.random() }
                .joinToString("")
        Log.d("MYLOG_nonce",t)
        Log.d("MYLOG_auth",userEntityAuth.email)
        Log.d("MYLOG_auth",userEntityAuth.password)

        val masterpass = Base64.getDecoder().decode(userEntityAuth.password)


        val login = AESUtil.encrypt(passwordEntity.login.toByteArray(), masterpass, t.toByteArray()) // 32 length Key authInfo.hash2
        val name = AESUtil.encrypt(passwordEntity.name.toByteArray(), masterpass, t.toByteArray()) // 32 length Key
        val encrypted_password = AESUtil.encrypt(passwordEntity.encrypted_password.toByteArray(), masterpass, t.toByteArray()) // 32 length Key

        Log.d("MYLOG_testE",userEntityAuth.password + " - " + String(masterpass))

        recordListRepository.insertRecord(RecordEntity(
            id = passwordEntity.id,
            name = String(name).replace("\n",""),//todo
            login = String(login).replace("\n",""),//todo
            nonce = t,
            encrypted_password = String(encrypted_password).replace("\n",""),//todo
            editedTime = passwordEntity.editedTime,
            creationTime = passwordEntity.creationTime,
            userId = userEntityAuth.id.toString(),
            isfavorite = false
        ))

    }
}
