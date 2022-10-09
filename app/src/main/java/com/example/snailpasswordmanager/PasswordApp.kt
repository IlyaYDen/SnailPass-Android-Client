package com.example.snailpasswordmanager

import android.app.Application
import com.example.snailpasswordmanager.di.AppComponent
import com.example.snailpasswordmanager.di.AppModule
import com.example.snailpasswordmanager.di.DaggerAppComponent

//@HiltAndroidApp
class PasswordApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}