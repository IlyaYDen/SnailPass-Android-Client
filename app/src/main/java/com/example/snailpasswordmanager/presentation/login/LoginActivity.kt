package com.example.snailpasswordmanager.presentation.login

import android.content.Intent
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
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity
import com.example.snailpasswordmanager.retrofit2.ServerApi
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var vm : LoginViewModel

    @Inject
    lateinit var vmFactory: LoginModelFactory


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (applicationContext as PasswordApp).appComponent.inject(this)


        vm = ViewModelProvider(this,vmFactory)
            .get(LoginViewModel::class.java)

        var buttonLogion: Button = findViewById(R.id.l_login_button)
        var buttonRegistration: Button = findViewById(R.id.l_registration_button)


        var loginText: EditText = findViewById(R.id.login_text)
        var passwordText: EditText = findViewById(R.id.password_text)

        //vm.onEvent()

        Log.d("test", "fffff")
        /*lifecycleScope.launchWhenCreated {
            val resp = try{
                serverApi.getapi()
            }catch (e: Exception){
                return@launchWhenCreated
            }
            if(resp.isSuccessful && resp.body() !=null){
                Log.d("test", "test  " + resp.body())
            }
        }*/


        buttonLogion.setOnClickListener {
            val hashpass = vm.passwordHash(passwordText.text.toString())

            val b = vm.onEvent(LoginEvent.Login(UserEntity(
                login = loginText.text.toString(),
                password = hashpass
            )))
            if(b !=null) {
                val intent = Intent(this, MainListActivity::class.java).apply {
                    putExtra("MASTER_HASH", true)
                }
                startActivity(intent)
                finish()
            }

        }
        buttonRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java).apply {}
            startActivity(intent)
        }





    }


}