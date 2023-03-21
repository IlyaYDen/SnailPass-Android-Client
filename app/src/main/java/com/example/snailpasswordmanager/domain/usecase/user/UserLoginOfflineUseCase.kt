package com.example.snailpasswordmanager.domain.usecase.user

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.LoginMode
import com.example.snailpasswordmanager.data.repository.AuthorizationData
import com.example.snailpasswordmanager.data.retrofit2.TokenAuthenticator
import com.example.snailpasswordmanager.domain.crypto.PBKDF2SHA512.Hash
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import java.util.*
import javax.inject.Inject

class UserLoginOfflineUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private var userEntityAuth: AuthorizationData
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

        userEntityAuth.user.id = userEntity.id
        userEntityAuth.user.email = userEntity.email
        userEntityAuth.user.password = encodedString
        userEntityAuth.user.hint = userEntity.hint
        userEntityAuth.user.isAdmin = userEntity.isAdmin

        TokenAuthenticator.hash = encodedString2

        return userRepository.getLoginOfflineAccess(
            userEntity,encodedString2
        )
    }
}
