package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.Config
import com.example.snailpasswordmanager.data.retrofit2.ServerApi
import com.example.snailpasswordmanager.data.retrofit2.Token
import com.example.snailpasswordmanager.data.retrofit2.TokenAuthenticator
import com.example.snailpasswordmanager.data.retrofit2.TokenInterceptor
import com.example.snailpasswordmanager.domain.model.UserEntity
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class RetrofitModule {
    @Provides
    fun providerRetrofit(token: Token, userEntityAuth: UserEntity) : ServerApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            //.authenticator(TokenAuthenticator(token,userEntityAuth))
            .addInterceptor(TokenInterceptor(token))
            .build()

        return Retrofit.Builder()
            .baseUrl("http://"+Config.ADRESS + ":" + Config.PORT)//todo
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ServerApi::class.java)
    }//saa@aaa.aaa
}