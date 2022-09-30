package com.example.snailpasswordmanager.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var vm : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        vm = ViewModelProvider(this).get(LoginViewModel::class.java)



    }

    fun registrationButton(view: View) {

        val intent = Intent(this, RegistrationActivity::class.java).apply {
            //putExtra("EXTRA_MESSAGE", "")
        }
        startActivity(intent)

    }

    fun loginButton(view: View) {


        val intent = Intent(this, MainListActivity::class.java).apply {
            putExtra("EXTRA_MESSAGE", "")
        }
        startActivity(intent)
        finish()

    }
}