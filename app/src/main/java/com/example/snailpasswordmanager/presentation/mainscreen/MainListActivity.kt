package com.example.snailpasswordmanager.presentation.mainscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snailpasswordmanager.data.repository.PasswordListRepositoryImpl
import com.example.snailpasswordmanager.databinding.ActivityMainListBinding
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.presentation.registration.RegistrationActivity

class MainListActivity : AppCompatActivity() {

    lateinit var bindingClass : ActivityMainListBinding

    //private val repository = PasswordListRepositoryImpl(baseContext)

    lateinit var viewModel : MainListViewModel

    private val adapter :PasswordListAdapter = PasswordListAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainListBinding.inflate(layoutInflater)


        setContentView(bindingClass.root)
        //setContentView(R.layout.activity_main_list)
        viewModel = ViewModelProvider(this)[MainListViewModel::class.java]

        bindingClass.ButtonAdd.setOnClickListener {
            viewModel.onEvent(PasswordsEvent.AddPassword(PasswordEntity(1,"test","test","test",1)))
        }

    }


    /*fun passwordAddButton(view: View) {

        val intent = Intent(this, RegistrationActivity::class.java).apply {
            //putExtra("EXTRA_MESSAGE", "")
        }
        startActivity(intent)

    }*/
}
