package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
//@InstallIn(SingletonComponent::class)
class AppModule(val context: Context) {

    lateinit var mApplication: Application

    @Provides
    fun provideContext() : Context {
        return context;
    }


    //@Provides
    //@Singleton
    //fun providesApplication(): Application {
    //    return mApplication
    //}

    @Provides
    @Singleton
    fun provideMainListViewModel(passwordUseCases: PasswordUseCases): MainListViewModel {
        return MainListViewModel(passwordUseCases)
    }
}