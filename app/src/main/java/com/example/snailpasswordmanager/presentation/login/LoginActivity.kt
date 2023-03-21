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
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
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


    lateinit var imageView : ImageView
    lateinit var loginText  : TextInputEditText
    lateinit var passwordText : TextInputEditText
    lateinit var loginButton : Button
    lateinit var registrationButton : Button
    lateinit var text : TextView
    lateinit var pb : ProgressBar

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


        //vm = ViewModelProvider(this,vmFactory) [LoginViewModel::class.java]
        vm = ViewModelProvider(this,vmFactory) [LoginViewModel::class.java]

        loginButton = findViewById(R.id.l_login_button)
        pb = findViewById(R.id.progressBarLogin)
        registrationButton = findViewById(R.id.l_registration_button)
        text = findViewById(R.id.welcomeText)


        loginText = findViewById(R.id.login_text)
        passwordText = findViewById(R.id.password_text)
        imageView = findViewById(R.id.imageView2)

        pb.visibility = View.GONE

        t = getSharedPreferences(PreferenceKeys.AUTH_SHARED_PREFERENCES,Context.MODE_PRIVATE)




        loginButton.setOnClickListener {
            currentFocus?.clearFocus()
            if(//todo validate
                loginText.text != null && loginText.text!!.length>5 &&
                passwordText.text != null && passwordText.text!!.length>9) {

                pb.visibility = View.VISIBLE
                userEntity = UserEntity(
                    email = loginText.text.toString().lowercase(),
                    password = passwordText.text.toString(),
                    hint = ""
                )
                lifecycleScope.launch {
                    vm.logInEvent(userEntity)
                }
            }
            else{
                loginText.error = getString(R.string.login_error)//"Please enter a valid email and password."
                //loginText.requestFocus()
            }



        }

        ApplicationUpdateUtility.checkForUpdate(this)

        //context.getExternalFilesDir(null), name

        if (File(
                getExternalFilesDir(null),
                "app-release.apk"
            ).exists()
        ) {
            File(
                getExternalFilesDir(null),
                "app-release.apk"
            ).delete()
        }
        //


        vm.sharedViewEffects
            .onEach {

                if(it.second == LoginMode.ONLINE || it.second == LoginMode.OFFLINE){
                    val intent = Intent(this, MainActivity::class.java).apply {

                        putExtra("MODE", it.second)
                    }

                    finish()
                    startActivity(intent)
                }
                else if(it.second == LoginMode.ERROR) {
                    var t = 3
                    if(it.first!="")
                        loginText.error = getString(R.string.login_error)
                }

                pb.visibility = View.GONE
            }
            .launchIn(lifecycleScope)


        registrationButton.setOnClickListener {
            currentFocus?.clearFocus()
            val intent = Intent(this, RegistrationActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair(loginButton, "snail_Login_Button"),
                Pair(registrationButton, "snail_SignUp_Button"),
                Pair(loginText, "snail_Username"),
                Pair(passwordText, "snail_Password"),
                Pair(imageView, "snail_Logo"),
                Pair(text,"snail_text")
            )
            startActivity(intent, options.toBundle())







        }





    }


}