package com.example.snailpasswordmanager.domain.usecase.user

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.AES.AESUtil
import com.example.snailpasswordmanager.domain.crypto.PBKDF2SHA512.hash
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.retrofit2.ServerApi
import com.example.snailpasswordmanager.retrofit2.Token
import okhttp3.Credentials
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOError
import java.io.IOException
import java.util.*
import javax.inject.Inject

class UserLoginUseCase(
    private val userRepository: UserRepository,
    var serverApi: ServerApi
) {


    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(userEntity: UserEntity): Token {
        val password = userEntity.password
        val salt = "1234" //todo
        val response = String()
        val iterations = 100_000
        val keyLength = 512
        val passwordChars = password.toCharArray()
        val saltBytes = Base64.getDecoder().decode(salt)

        val hashedBytes: ByteArray = hash.hashPassword(passwordChars, saltBytes, iterations, keyLength)

        val encodedString: String = Base64.getEncoder().encodeToString(hashedBytes)
        Log.d("test", encodedString)

        val credentials: String = Credentials.basic(userEntity.email,encodedString)
        try{
            val t = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ServerApi::class.java)
            val a = t.getLogin(credentials)
     //       val resp = serverApi.

            Log.d("test", "true ")
            return a

        }catch (e: IOException){
            Log.d("test","Internet error")
        }catch (e: HttpException){
            Log.d("test","Server error")
        }

        return Token("-")
    }
}
