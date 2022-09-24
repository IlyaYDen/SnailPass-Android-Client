package com.example.snailpasswordmanager.presentation.listItem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.snailpasswordmanager.databinding.ActivityListItemViewBinding
import com.example.snailpasswordmanager.databinding.ActivityMainListBinding

class ListItemView : AppCompatActivity() {

    lateinit var bindingClass : ActivityListItemViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityListItemViewBinding.inflate(layoutInflater)

        setContentView(bindingClass.root)



        bindingClass.editTextService.setText(intent.getStringExtra("SERVICE"))
        bindingClass.editTextLogin.setText(intent.getStringExtra("LOGIN"))
        bindingClass.editTextPassword.setText(intent.getStringExtra("PASSWORD"))

        bindingClass.buttonSave.setOnClickListener {

        }
        bindingClass.checkBoxShowPassword.setOnClickListener {
            if(bindingClass.checkBoxShowPassword.isChecked){

            }
        }

    }
}