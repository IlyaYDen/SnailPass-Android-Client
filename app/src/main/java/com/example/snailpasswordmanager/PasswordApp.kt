
package com.example.snailpasswordmanager

import android.app.Application
import com.example.snailpasswordmanager.di.AppComponent
import com.example.snailpasswordmanager.di.AppModule
import com.example.snailpasswordmanager.di.DaggerAppComponent

//@HiltAndroidApp
class PasswordApp : Application() {
//todo make selection other color (now it is very grey)
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}