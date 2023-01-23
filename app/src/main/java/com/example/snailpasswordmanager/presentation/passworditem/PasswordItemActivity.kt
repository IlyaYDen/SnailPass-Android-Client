package com.example.snailpasswordmanager.presentation.passworditem

import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.text.InputType.*
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.databinding.ActivityPasswordItemBinding
import com.example.snailpasswordmanager.domain.model.RecordEntity
import java.util.*
import javax.inject.Inject


class PasswordItemActivity : AppCompatActivity() {

    lateinit var bindingClass : ActivityPasswordItemBinding

    @Inject
    lateinit var vmFactory: PasswordViewModelFactory

    private lateinit var viewModel : PasswordViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityPasswordItemBinding.inflate(layoutInflater)

        (applicationContext as PasswordApp).appComponent.inject(this)

        setContentView(bindingClass.root)

        viewModel = ViewModelProvider(this,vmFactory)
            .get(PasswordViewModel::class.java)
        if( intent.getBooleanExtra("MODE",false)) {

            val id = intent.getSerializableExtra("ID") as UUID
            val service = intent.getStringExtra("SERVICE")
            val login = intent.getStringExtra("LOGIN")
            val password = intent.getStringExtra("PASSWORD")
            bindingClass.deleteButton.visibility = View.VISIBLE

            bindingClass.editTextPassword.setText(password)
            bindingClass.editTextService.setText(service)
            bindingClass.editTextLogin.setText(login)
            bindingClass.deleteButton.setOnClickListener {
                viewModel.deletePassword(recordEntity = RecordEntity(
                    name = bindingClass.editTextService.text.toString(),
                    login = bindingClass.editTextLogin.text.toString(),
                    encrypted_password = bindingClass.editTextPassword.text.toString(),
                    editedTime = System.currentTimeMillis().toString(),//todo
                    id = id,
                    creationTime = "",
                    nonce = "",
                    userId = "",
                    isfavorite = false
                ))
                finish()
            }


            bindingClass.buttonSave.setOnClickListener {
                viewModel.addPassword(
                    passwordEntity = RecordEntity(
                        name = bindingClass.editTextService.text.toString(),
                        login = bindingClass.editTextLogin.text.toString(),
                        encrypted_password = bindingClass.editTextPassword.text.toString(),
                        editedTime = System.currentTimeMillis().toString(),//todo
                        id = id,
                        creationTime = "",
                        nonce = "",
                        userId = "",
                        isfavorite = false
                    )
                )
                finish()
            }
        }
        else {

            bindingClass.buttonSave.setOnClickListener {
                viewModel.addPassword(
                    passwordEntity = RecordEntity(
                        name = bindingClass.editTextService.text.toString(),
                        login = bindingClass.editTextLogin.text.toString(),
                        encrypted_password = bindingClass.editTextPassword.text.toString(),
                        editedTime = System.currentTimeMillis().toString(),//todo
                        id = UUID.randomUUID(),
                        creationTime = "",
                        nonce = "",
                        userId = "",
                        isfavorite = false
                    )
                )
                finish()
            }
        }

        bindingClass.checkBoxShowPassword.setOnClickListener {
            Log.d("MYLOG_test", "checkBoxShowPassword click " + bindingClass.editTextPassword.inputType)

            if (bindingClass.checkBoxShowPassword.isChecked) {
                bindingClass.editTextPassword.inputType = TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

                Log.d("MYLOG_test", "checkBoxShowPassword isActivated")
            } else {

                Log.d("MYLOG_test", "checkBoxShowPassword isNotActivated")
                bindingClass.editTextPassword.inputType = 129//InputType.TYPE_TEXT_VARIATION_PASSWORD

            }
        }

    }
}