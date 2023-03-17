package com.example.snailpasswordmanager.domain.usecase.additionalFields

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.data.repository.AuthorizationData
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import java.util.*
import javax.inject.Inject

class InsertField @Inject constructor(
    private val additionalFieldsRepository: AdditionalFieldsRepository,
    private val userEntityAuth: AuthorizationData
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(subList: MutableList<RecordAddFieldEntity>){
        for(t in subList) {


            val masterpass = Base64.getDecoder().decode(userEntityAuth.user.password.toByteArray())

            val nonceName = nonceGen()
            val name = AESUtil.encrypt(t.name.toByteArray(),masterpass, nonceName.toByteArray())

            val nonceValue = nonceGen()
            val value = AESUtil.encrypt(t.value.toByteArray(),masterpass, nonceValue.toByteArray())

//String(Base64.getEncoder().encode(name)).replace("\n","") + ":" + String(Base64.getEncoder().encode(nameNonce.toByteArray())),
            additionalFieldsRepository.insertField(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = String(Base64.getEncoder().encode(name)) + ":"+String(Base64.getEncoder().encode(nonceName.toByteArray())),
                    value = String(Base64.getEncoder().encode(value)) + ":"+String(Base64.getEncoder().encode(nonceValue.toByteArray())),
                    record_id = t.record_id
                )
            )
        }//saa@aaa.aaa
    }//mail@snail.corp snailsnailsnail

    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789+=-"
    fun nonceGen() = (0..15)
        .map { charset.random() }
        .joinToString("")
}