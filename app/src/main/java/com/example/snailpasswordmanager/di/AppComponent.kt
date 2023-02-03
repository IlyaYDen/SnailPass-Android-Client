package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.presentation.login.LoginActivity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, RetrofitModule::class])
@Singleton
interface AppComponent {
    fun inject(mainListActivity: MainListActivity)
    fun inject(registrationActivity: RegistrationActivity)
    fun inject(loginActivity: LoginActivity)
}