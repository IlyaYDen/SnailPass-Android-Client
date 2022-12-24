package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.presentation.login.LoginActivity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.passworditem.PasswordItemActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class, RetrofitModule::class])
interface AppComponent {
    fun inject(mainListActivity: MainListActivity)
    fun inject(passwordItemActivity: PasswordItemActivity)
    fun inject(registrationActivity: RegistrationActivity)
    fun inject(loginActivity: LoginActivity)
}