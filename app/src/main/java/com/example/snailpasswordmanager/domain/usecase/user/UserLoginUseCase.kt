package com.example.snailpasswordmanager.domain.usecase.user

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.domain.crypto.PBKDF2SHA512.Hash
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.data.retrofit2.TokenAuthenticator.Companion.hash
import java.util.*

class UserLoginUseCase(
    private val userRepository: UserRepository,
    private var userEntityAuth: UserEntity
) {


    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(userEntity: UserEntity): Pair<String, LoginMode> {
        val password = userEntity.password
        val salt = userEntity.email

        val iterations = 100_000
        val keyLength = 256

        val hashedBytes: ByteArray = Hash.hashPassword(password.toCharArray(), salt.toByteArray(), iterations, keyLength)
        val encodedString: String = Base64.getEncoder().encodeToString(hashedBytes)

        val hashedBytes2: ByteArray = Hash.hashPassword(password.toCharArray(), salt.toByteArray(), 120_000, keyLength)
        val encodedString2: String = Base64.getEncoder().encodeToString(hashedBytes2)

        userEntityAuth.id = userEntity.id
        userEntityAuth.email = userEntity.email
        userEntityAuth.password = encodedString
        userEntityAuth.hint = userEntity.hint
        userEntityAuth.isAdmin = userEntity.isAdmin

        hash = encodedString2

        return userRepository.getLoginAccess(
            userEntity,encodedString2
        )
    }
}
