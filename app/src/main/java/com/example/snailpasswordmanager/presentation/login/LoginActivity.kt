package com.example.snailpasswordmanager.presentation.login

import android.annotation.SuppressLint
import android.content.*
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.*
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.login.components.LoginScreen
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var vm : LoginViewModel

    @Inject
    lateinit var vmFactory: LoginModelFactory

    lateinit var text : TextView

    // val Context.appStartupParamsDataStore: DataStore<Preferences> by dataStore(
    //     fileName = "app_startup_params.pb",
    //     serializer = AppStartupParamsSerializer
    // )
    private lateinit var t : SharedPreferences

    private lateinit var userEntity: UserEntity

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        (applicationContext as PasswordApp).appComponent.inject(this)

        vm = ViewModelProvider(this, vmFactory)[LoginViewModel::class.java]
        setContent {
            LoginScreen()
        }
    }
}