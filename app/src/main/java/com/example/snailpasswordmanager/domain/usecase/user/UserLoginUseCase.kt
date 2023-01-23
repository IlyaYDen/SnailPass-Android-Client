package com.example.snailpasswordmanager.domain.usecase.user

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.PBKDF2SHA512.Hash
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import okhttp3.Credentials
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*

class UserLoginUseCase(
    private val userRepository: UserRepository,
    private var userEntityAuth: UserEntity
) {


    @RequiresApi(Build.VERSION_CODES.O)//todo base64 android
    suspend operator fun invoke(userEntity: UserEntity): Boolean {
        val password = userEntity.password
        val salt = userEntity.email

        val iterations = 100_000
        val keyLength = 256

        val hashedBytes: ByteArray = Hash.hashPassword(password.toCharArray(), salt.toByteArray(), iterations, keyLength)

        val encodedString: String = Base64.getEncoder().encodeToString(hashedBytes)

        //Log.d("MYLOG_login", userEntity.email)
        Log.d("MYLOG_login", encodedString)
        //Log.d("MYLOG_login", String(hashedBytes))

        userEntityAuth.id = userEntity.id
        userEntityAuth.email = userEntity.email
        userEntityAuth.password = encodedString
        userEntityAuth.hint = userEntity.hint
        userEntityAuth.isAdmin = userEntity.isAdmin

        Log.d("MYLOG_authEntity", "UserLoginUseCase: " + userEntityAuth.toString())
        return userRepository.getloginAccess(
            userEntity,encodedString
        )
    }
}
