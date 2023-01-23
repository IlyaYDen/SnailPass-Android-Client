package com.example.snailpasswordmanager.presentation.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.PreferenceKeys
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var vm : LoginViewModel

    @Inject
    lateinit var vmFactory: LoginModelFactory

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

        val buttonLogion: Button = findViewById(R.id.l_login_button)
        val buttonRegistration: Button = findViewById(R.id.l_registration_button)


        val loginText: EditText = findViewById(R.id.login_text)
        val passwordText: EditText = findViewById(R.id.password_text)

        t = getSharedPreferences(PreferenceKeys.AUTH_SHARED_PREFERENCES,Context.MODE_PRIVATE)

       buttonLogion.setOnClickListener {
            val hashpass = vm.passwordHash(passwordText.text.toString())

           if(
               !loginText.text.isEmpty() && loginText.text.length>5 &&
               !passwordText.text.isEmpty() && passwordText.text.length>8) {
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
                    val intent = Intent(this, MainListActivity::class.java)
                    //t.edit().putString(PreferenceKeys.EMAIL,userEntity.email).apply()
                    //t.edit().putString(PreferenceKeys.HASH_MASTER_PASSWORD,it.first).apply()
                    //t.edit().putString(PreferenceKeys.TOKEN,it.second).apply()
                    finish()
                    startActivity(intent)
                }
            }
            .launchIn(lifecycleScope)


        buttonRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }





    }


}