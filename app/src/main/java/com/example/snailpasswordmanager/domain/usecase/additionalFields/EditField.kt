package com.example.snailpasswordmanager.domain.usecase.additionalFields

import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.AdditionalFieldsRepository
import java.util.*
import javax.inject.Inject

class EditField @Inject constructor(
    private val additionalFieldsRepository: AdditionalFieldsRepository,
    private val userEntityAuth: UserEntity
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(subList: MutableList<RecordAddFieldEntity>){
        var a = 1;
        val list : MutableList<RecordAddFieldEntity> = mutableListOf()
        for(t in subList) {

            val masterpass = Base64.decode(userEntityAuth.password.toByteArray(),0)
            val nonce = nonceGen()
            val name = AESUtil.encrypt(t.value.toByteArray(),masterpass, nonce.toByteArray())

            list.add(
                RecordAddFieldEntity(
                    id = t.id,
                    field_name = t.field_name,
                    value = String(name)+":"+nonce,
                    record_id = t.record_id
            )
            )
        }

        additionalFieldsRepository.editFieldList(
            list
        )
    }

    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789+=-"
    fun nonceGen() = (0..15)
        .map { charset.random() }
        .joinToString("")
}