package com.example.snailpasswordmanager.di

import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.passworditem.PasswordItemActivity
import dagger.Component
import dagger.Module

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(mainListActivity: MainListActivity)
    fun inject(passwordItemActivity: PasswordItemActivity)
}