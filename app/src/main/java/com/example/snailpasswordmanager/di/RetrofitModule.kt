package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.domain.repository.UserRepository
import com.example.snailpasswordmanager.domain.usecase.user.UserLoginUseCase
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.retrofit2.ServerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
@Module
class RetrofitModule {

    @Provides
    fun providerRetrofit() : ServerApi {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerApi::class.java)
    }
}