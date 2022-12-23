package com.example.snailpasswordmanager.presentation.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.login.LoginEvent
import com.example.snailpasswordmanager.presentation.login.LoginViewModel
import com.example.snailpasswordmanager.retrofit2.ServerApi

class RegistrationActivity : AppCompatActivity() {

    private lateinit var vm : RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val buttonRegistration: Button = findViewById(R.id.r_registration_button)

        vm = ViewModelProvider(this)[RegistrationViewModel::class.java]

        buttonRegistration.setOnClickListener {
            //val b = vm.registrationEvent(); TODO registration
        }
    }

    fun gotoLogin(view: View) {
        finish();
    }
}