package com.example.snailpasswordmanager.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.example.snailpasswordmanager.domain.usecase.additionalFields.FieldUseCases
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.domain.usecase.user.UserUseCases
import com.example.snailpasswordmanager.presentation.login.LoginViewModel
import com.example.snailpasswordmanager.presentation.recordList.RecordListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
//@InstallIn(SingletonComponent::class)
class AppModule(val context: Context) {

    lateinit var mApplication: Application

    @Provides
    fun provideContext() : Context {
        return context
    }


    @Singleton
    @Provides
    fun provideApplication(context : Context): ConnectivityManager {
        val connectivityManager = context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager
    }
    //@Provides
    //@Singleton
    //fun providesApplication(): Application {
    //    return mApplication
    //}

    @Provides
    @Singleton
    fun provideMainListViewModel(passwordUseCases: PasswordUseCases, fieldUseCases: FieldUseCases): RecordListViewModel {
        return RecordListViewModel(passwordUseCases,fieldUseCases)
    }
    @Provides
    @Singleton
    fun provideLoginViewModel(useCases: UserUseCases): LoginViewModel {
        return LoginViewModel(useCases)
    }
}//todo clean di