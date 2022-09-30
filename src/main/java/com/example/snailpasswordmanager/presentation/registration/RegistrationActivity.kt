package com.example.snailpasswordmanager.presentation.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.snailpasswordmanager.R

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun gotoLogin(view: View) {
        finish();
    }
}