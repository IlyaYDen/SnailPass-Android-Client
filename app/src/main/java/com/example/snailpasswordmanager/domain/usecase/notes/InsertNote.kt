package com.example.snailpasswordmanager.domain.usecase.notes

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.model.InvalidRecordException
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.NoteListRepository
import java.security.SecureRandom
import java.util.*
import javax.inject.Inject
import kotlin.math.log

class InsertNote @Inject constructor(
    private val noteListRepository: NoteListRepository,
    private val userEntityAuth: UserEntity
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(noteEntity: NoteEntity){

        if (noteEntity.name.isBlank()) throw InvalidRecordException("The service can't be empty.") //todo make validation for it
        if (noteEntity.content.isBlank()) throw InvalidRecordException("The service can't be empty.") //todo make validation for it

        val masterpass = Base64.getDecoder().decode(userEntityAuth.password)

        val (name, nameNonce) = encryptData(noteEntity.name.toByteArray(), masterpass)
        val (content, contentNonce) = encryptData(noteEntity.content.toByteArray(), masterpass)

        noteListRepository.insertNote(
            NoteEntity(
                id = noteEntity.id,
                name = encodeData(name,nameNonce),
                content = encodeData(content,contentNonce),
                is_favorite = noteEntity.is_favorite,
                is_deleted = noteEntity.is_deleted,
                creation_time = noteEntity.creation_time,
                update_time = noteEntity.update_time,
                user_id = noteEntity.user_id
            )
        )/*
        recordListRepository.insertRecord(
            RecordEntity(
            id = passwordEntity.id,
            name = encodeData(name, nameNonce),
            login = encodeData(login, loginNonce),
            encrypted_password = encodeData(encryptedPassword, encryptedPasswordNonce),
            editedTime = passwordEntity.editedTime,
            creationTime = passwordEntity.creationTime,
            userId = userEntityAuth.id.toString(),
            isfavorite = passwordEntity.isfavorite
        )
        )*/
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

    fun generateNonce(): ByteArray {
        val random = SecureRandom()
        val nonce = ByteArray(16)
        random.nextBytes(nonce)
        return nonce
    }
}