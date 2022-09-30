package com.example.snailpasswordmanager.presentation.passworditem

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.databinding.ActivityPasswordItemBinding
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.presentation.mainscreen.MainListViewModelFactory
import javax.inject.Inject


class PasswordItemActivity : AppCompatActivity() {

    lateinit var bindingClass : ActivityPasswordItemBinding

    @Inject
    lateinit var vmFactory: PasswordViewModelFactory

    private lateinit var viewModel : PasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityPasswordItemBinding.inflate(layoutInflater)

        (applicationContext as PasswordApp).appComponent.inject(this)

        setContentView(bindingClass.root)

        viewModel = ViewModelProvider(this,vmFactory)
            .get(PasswordViewModel::class.java)
        if( intent.getBooleanExtra("MODE",false)) {
            val service = intent.getStringExtra("SERVICE")
            val password = intent.getStringExtra("PASSWORD")
            val login = intent.getStringExtra("LOGIN")
            val id = intent.getIntExtra("ID",0)
            bindingClass.deleteButton.visibility = View.VISIBLE

            bindingClass.editTextPassword.setText(password)
            bindingClass.editTextService.setText(service)
            bindingClass.editTextLogin.setText(login)
            bindingClass.deleteButton.setOnClickListener {
                viewModel.deletePassword(passwordEntity = PasswordEntity(
                    service = bindingClass.editTextService.text.toString(),
                    login = bindingClass.editTextLogin.text.toString(),
                    password = bindingClass.editTextPassword.text.toString(),
                    timestamp = System.currentTimeMillis(),
                    id = id
                ))
                finish()
            }


            bindingClass.buttonSave.setOnClickListener {
                viewModel.addPassword(
                    passwordEntity = PasswordEntity(
                        service = bindingClass.editTextService.text.toString(),
                        login = bindingClass.editTextLogin.text.toString(),
                        password = bindingClass.editTextPassword.text.toString(),
                        timestamp = System.currentTimeMillis(),
                        id = id
                    )
                )
                finish()
            }
            bindingClass.checkBoxShowPassword.setOnClickListener(){

            }
        }
        else {

            bindingClass.buttonSave.setOnClickListener {
                viewModel.addPassword(
                    passwordEntity = PasswordEntity(
                        service = bindingClass.editTextService.text.toString(),
                        login = bindingClass.editTextLogin.text.toString(),
                        password = bindingClass.editTextPassword.text.toString(),
                        timestamp = System.currentTimeMillis()
                    )
                )
                finish()
            }
        }

    }
}