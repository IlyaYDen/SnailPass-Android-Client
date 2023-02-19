package com.example.snailpasswordmanager.data.retrofit2

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.snailpasswordmanager.Config
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.google.gson.Gson
import okhttp3.*
import java.util.*
import javax.inject.Inject

class TokenAuthenticator constructor(
    var token: Token,
    var userEntityAuth: UserEntity
    ) : Authenticator {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun authenticate(route: Route?, response: Response): Request {
        Log.d("MYLOG_testT","TokenAuthenticator")
        val credentials: String = Credentials.basic(userEntityAuth.email,userEntityAuth.password)
        val request = Request.Builder()
            .url("http://"+ Config.ADRESS + ":" + Config.PORT + "/login")
            .get()
            .header("Authorization", credentials)
            .build()

        Log.d("MYLOG_testEEA","TokenInterceptor T " + token.token)
        Log.d("MYLOG_testEEA","TokenInterceptor E " + userEntityAuth.email)
        Log.d("MYLOG_testEEA","TokenInterceptor P " + userEntityAuth.password)

        val loginResponse = OkHttpClient().newCall(request).execute()
        val jsonBody = loginResponse.body?.string()
        val tokenResponse = Gson().fromJson(jsonBody, Token::class.java)
        token.token = tokenResponse.token



        return response.request.newBuilder()
            .header("x-access-token", token.token)
            .build()
    }

}
class TokenInterceptor @Inject constructor(
    val token: Token
    )  : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("MYLOG_testT","TokenInterceptor ")
        Log.d("MYLOG_testEEI","TokenInterceptor " + token.token)

        val request = chain.request()
            .newBuilder()
            .addHeader("x-access-token",token.token)
            .build()
        return chain.proceed(request)
    }

}/*
data class AuthInfo(
    var login: String,
    var hash: String,
    var hash2: ByteArray,
    var uuid: UUID
)*/