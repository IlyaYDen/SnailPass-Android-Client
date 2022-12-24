package com.example.snailpasswordmanager.domain.usecase.user

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.domain.crypto.PBKDF2SHA512.hash
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.retrofit2.Registration
import com.example.snailpasswordmanager.retrofit2.ServerApi
import com.example.snailpasswordmanager.retrofit2.Token
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*


class UserRegisterUseCase (
    private val userRepository: UserRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(userEntity: UserEntity){
        val password = userEntity.password
        val salt = "1234" //todo
        val iterations = 100_000
        val keyLength = 512
        val passwordChars = password.toCharArray()
        val saltBytes = Base64.getDecoder().decode(salt)

        val hashedBytes: ByteArray = hash.hashPassword(passwordChars, saltBytes, iterations, keyLength)

        val encodedString: String = Base64.getEncoder().encodeToString(hashedBytes)
        Log.d("test", encodedString)
        val reg = Registration(
                id = userEntity.id.toString(),
                email = userEntity.email,
                master_password_hash = encodedString,
                nonce = userEntity.hint,
            )
        try {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()


            val t = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ServerApi::class.java)
            //       val resp = serverApi.
            t.registration(reg)

        } catch (e: IOException) {
            Log.d("test", "Internet error")
        } catch (e: HttpException) {
            Log.d("test", "Server error " )
        }

    }
}