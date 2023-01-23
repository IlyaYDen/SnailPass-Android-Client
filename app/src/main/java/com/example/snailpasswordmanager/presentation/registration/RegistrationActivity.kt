package com.example.snailpasswordmanager.presentation.registration

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    private lateinit var vm : RegistrationViewModel

    @Inject
    lateinit var vmFactory: RegistrationModelFactory

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val buttonRegistration: Button = findViewById(R.id.r_registration_button)
        val buttonLogin: Button = findViewById(R.id.r_login_button)

        val tvLogin: TextView = findViewById(R.id.login_text)
        val tvPassword_text: TextView = findViewById(R.id.password_text)
        val tvRepeat_password_text: TextView = findViewById(R.id.repeat_password_text)
        val tvHint_text: TextView = findViewById(R.id.hint_text)


        (applicationContext as PasswordApp).appComponent.inject(this)

        vm = ViewModelProvider(this,vmFactory)[RegistrationViewModel::class.java]



        vm.boolean
            .onEach {

                if(it){
                    //val intent = Intent(this, MainListActivity::class.java)
                    finish()
                    //startActivity(intent)
                }
            }
            .launchIn(lifecycleScope)

        buttonRegistration.setOnClickListener {
            //val b = vm.registrationEvent(); TODO registration
            if(
                tvLogin.text.isNotEmpty() && tvPassword_text.text.isNotEmpty()
                && tvRepeat_password_text.text.isNotEmpty() && tvHint_text.text.isNotEmpty()
                && tvRepeat_password_text.text.toString().equals(tvPassword_text.text.toString())
                && tvRepeat_password_text.text.length>5 && tvLogin.text.length>8
            ) {
                vm.registrationEvent(
                    UserEntity(
                        id = UUID.randomUUID(),
                        hint = tvHint_text.text.toString(),
                        email = tvLogin.text.toString(),
                        password = tvPassword_text.text.toString()
                    )
                )
            }
        }
        buttonLogin.setOnClickListener { finish() }

    }

}

