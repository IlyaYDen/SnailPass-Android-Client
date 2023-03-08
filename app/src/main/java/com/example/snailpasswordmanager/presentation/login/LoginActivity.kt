package com.example.snailpasswordmanager.presentation.login

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Pair
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.PreferenceKeys
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.mainActivity.MainActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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

   // val Context.appStartupParamsDataStore: DataStore<Preferences> by dataStore(
   //     fileName = "app_startup_params.pb",
   //     serializer = AppStartupParamsSerializer
   // )
    private lateinit var t : SharedPreferences

    private lateinit var userEntity: UserEntity

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (applicationContext as PasswordApp).appComponent.inject(this)




       //vm = ViewModelProvider(this,vmFactory) [LoginViewModel::class.java]
       vm = ViewModelProvider(this,vmFactory) [LoginViewModel::class.java]

        loginButton = findViewById(R.id.l_login_button)
        registrationButton = findViewById(R.id.l_registration_button)
        text = findViewById(R.id.welcomeText)


        loginText = findViewById(R.id.login_text)
        passwordText = findViewById(R.id.password_text)
        imageView = findViewById(R.id.imageView2)


        t = getSharedPreferences(PreferenceKeys.AUTH_SHARED_PREFERENCES,Context.MODE_PRIVATE)

        loginButton.setOnClickListener {
            val hashpass = vm.passwordHash(passwordText.text.toString())

           if(
               loginText.text != null && loginText.text!!.length>5 &&
               passwordText.text != null && passwordText.text!!.length>8) {
               userEntity = UserEntity(
                   email = loginText.text.toString(),
                   password = hashpass,
                   hint = ""
               )
               lifecycleScope.launch {
                   vm.logInEvent(userEntity)
               }
           }



        }
        vm.boolean
            .onEach {

                if(it){
                    val intent = Intent(this, MainActivity::class.java)
                    //t.edit().putString(PreferenceKeys.EMAIL,userEntity.email).apply()
                    //t.edit().putString(PreferenceKeys.HASH_MASTER_PASSWORD,it.first).apply()
                    //t.edit().putString(PreferenceKeys.TOKEN,it.second).apply()
                    finish()
                    startActivity(intent)
                }
            }
            .launchIn(lifecycleScope)


        registrationButton.setOnClickListener {
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