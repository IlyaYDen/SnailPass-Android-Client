package com.example.snailpasswordmanager.presentation.login

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.app.DownloadManager
import android.app.usage.NetworkStatsManager
import android.content.*
import android.net.ConnectivityManager
import android.net.Network
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.Pair
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.snailpasswordmanager.*
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.mainActivity.MainActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import com.example.snailpasswordmanager.utils.ApplicationUpdateUtility
import com.example.snailpasswordmanager.utils.NetworkUtils
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.io.File
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
            hello()
        }
    }
}

@Composable
fun hello() {
    Text(text = "test")
}

@Composable
@Preview
fun LoginActivityPreview() {
    hello()
}