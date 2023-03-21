package com.example.snailpasswordmanager.presentation.registration

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.UserEntity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    private lateinit var vm : RegistrationViewModel

    @Inject
    lateinit var vmFactory: RegistrationModelFactory

    lateinit var buttonRegistration: Button
    lateinit var buttonLogin: Button
    lateinit var tvLogin: TextView
    lateinit var tvPassword_text: TextView
    lateinit var tvRepeat_password_text: TextView
    lateinit var tvHint_text: TextView
    lateinit var pb: ProgressBar

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonRegistration = findViewById(R.id.r_registration_button)
        buttonLogin = findViewById(R.id.r_login_button)

        tvLogin = findViewById(R.id.login_text)
        tvPassword_text = findViewById(R.id.password_text)
        tvRepeat_password_text = findViewById(R.id.repeat_password_text)
        tvHint_text = findViewById(R.id.hint_text)

        pb = findViewById(R.id.progressBarRegister)

        pb.visibility = View.GONE

        (applicationContext as PasswordApp).appComponent.inject(this)

        vm = ViewModelProvider(this,vmFactory)[RegistrationViewModel::class.java]



        vm.boolean
            .onEach {

                if(it.first){
                    finish()
                }
                else
                    if(it.second!="")
                        if(it.second == "\"Resource Already Exists. User with this email already exists\"")
                            tvLogin.error = getString(R.string.user_already_exists)
                pb.visibility = View.GONE
            }
            .launchIn(lifecycleScope)

        buttonRegistration.setOnClickListener {
            //val b = vm.registrationEvent();
            if(
                validateUsername() && validatePassword() && validateRepeatPassword()
            ) {
                pb.visibility = View.VISIBLE
                vm.registrationEvent(
                    UserEntity(
                        id = UUID.randomUUID(),
                        hint = tvHint_text.text.toString(),
                        email = tvLogin.text.toString().lowercase(),
                        password = tvPassword_text.text.toString()
                    )
                )
            } else {
                if(validateUsername())
                    tvLogin.error = getString(R.string.invalid_username)
                if(validatePassword())
                    tvPassword_text.error = getString(R.string.invalid_username)
                if(validateRepeatPassword())
                    tvRepeat_password_text.error = getString(R.string.invalid_username)
            }
        }
        buttonLogin.setOnClickListener { this.onBackPressed(); }

    }

    fun validateUsername() : Boolean{

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        val value: String = tvLogin.text.toString()
        val tvPassword_text: TextView = findViewById(R.id.password_text)
        val tvRepeat_password_text: TextView = findViewById(R.id.repeat_password_text)
        val tvHint_text: TextView = findViewById(R.id.hint_text)

        if(value.isEmpty()){
            tvLogin.error = "Field cannot be empty"
            return false
        }
        else if(!value.matches(Regex(emailPattern))){

            tvLogin.error = "Invalid email"
            return false
        }
        else{
            tvLogin.error = null
            return true
        }
    }
    fun validatePassword() : Boolean{

        val value = tvPassword_text.text.toString()

        if(value.isEmpty()){
            tvPassword_text.error = "Field cannot be empty"
            return false
        }
        if(value.length < 5){
            tvPassword_text.error = "Password too short"
            return false
        }
        else{
            tvPassword_text.error = null
            return true
        }
    }
    fun validateRepeatPassword() : Boolean{

        val value = tvRepeat_password_text.text.toString()

        if(value.isEmpty()){
            tvRepeat_password_text.error = "Field cannot be empty"
            return false
        }
        else if(tvPassword_text.text.toString() != value){
            tvRepeat_password_text.error = "Password is not match"
            return false
        }
        else{
            tvRepeat_password_text.error = null
            return true
        }
    }
}

