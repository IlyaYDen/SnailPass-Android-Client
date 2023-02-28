package com.example.snailpasswordmanager.domain.usecase.user

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.PBKDF2SHA512.Hash
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.data.retrofit2.Registration
import java.util.*


class UserRegisterUseCase (
    private val userRepository: UserRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(userEntity: UserEntity) : Boolean{
        val password = userEntity.password
        val salt = userEntity.email

        val iterations = 120_000
        val keyLength = 256

        val hashedBytes: ByteArray = Hash.hashPassword(password.toCharArray(), salt.toByteArray(), iterations, keyLength)

        val encodedString: String = Base64.getEncoder().encodeToString(hashedBytes)

        //Log.d("MYLOG_test", encodedString)
        val reg = Registration(
                id = userEntity.id,
                email = userEntity.email,
                master_password_hash = encodedString,
                hint = userEntity.hint,
            )

        return userRepository.addUser(reg)


    }
}