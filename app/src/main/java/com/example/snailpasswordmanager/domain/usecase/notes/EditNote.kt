package com.example.snailpasswordmanager.domain.usecase.notes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.NoteListRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class EditNote @Inject constructor(
    private val noteListRepository: NoteListRepository,
    private var userEntityAuth: UserEntity
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(noteEntity: NoteEntity) {
        if(noteEntity.name.isBlank())
            throw InvalidRecordException("The service can't be empty.")
        if(noteEntity.content.isBlank())
            throw InvalidRecordException("The login can't be empty.")


        val masterpass = Base64.getDecoder().decode(userEntityAuth.password.toByteArray())
        //val masterpass = userEntityAuth.password.toByteArray()// Base64.decode(userEntityAuth.password.toByteArray(),0)

        val nameNonce = nonceGen()
        val name = AESUtil.encrypt(noteEntity.name.toByteArray(), masterpass, nameNonce.toByteArray())

        val contentNonce = nonceGen()
        val content = AESUtil.encrypt(noteEntity.content.toByteArray(), masterpass, contentNonce.toByteArray())

        noteListRepository.editNote(
            NoteEntity(
                id = noteEntity.id,
                name = String(Base64.getEncoder().encode(name)) + ":" + String(Base64.getEncoder().encode(nameNonce.toByteArray())),
                content = String(Base64.getEncoder().encode(content)) + ":" + String(Base64.getEncoder().encode(contentNonce.toByteArray())),
                is_favorite = noteEntity.is_favorite,
                creation_time = noteEntity.creation_time,
                is_deleted = noteEntity.is_deleted,
                user_id = noteEntity.user_id,
                update_time = noteEntity.update_time
            )

        )

    }
    val charset = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz0123456789+=-"
    fun nonceGen() = (0..15)
        .map { charset.random() }
        .joinToString("")
}
