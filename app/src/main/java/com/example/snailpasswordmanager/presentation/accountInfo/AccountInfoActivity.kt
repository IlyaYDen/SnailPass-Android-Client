package com.example.snailpasswordmanager.presentation.accountInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityAccountInfoListBinding
import com.example.snailpasswordmanager.databinding.ActivityRecordListBinding
import com.example.snailpasswordmanager.domain.model.RecordInfoEntity
import com.example.snailpasswordmanager.presentation.login.LoginViewModel
import com.example.snailpasswordmanager.presentation.mainscreen.PasswordListAdapter
import java.util.*
import kotlin.collections.ArrayList

class AccountInfoActivity: AppCompatActivity() {


    lateinit var bindingClass : ActivityAccountInfoListBinding

    private lateinit var vm : AccountInfoViewModel

    private val adapter : AccountInfoAdapter = AccountInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_account_info_list)

        bindingClass = /*ActivityMainListBinding*/ActivityAccountInfoListBinding.inflate(layoutInflater)

        setContentView(bindingClass.root)
        bindingClass.apply {

            rv.layoutManager = LinearLayoutManager(this@AccountInfoActivity)
            rv.adapter = adapter

        }
        //(applicationContext as PasswordApp).appComponent.inject(this)


        val id = intent.getSerializableExtra("ID") as UUID
        val service = intent.getStringExtra("SERVICE")
        val login = intent.getStringExtra("LOGIN")
        val password = intent.getStringExtra("PASSWORD")


        var list = ArrayList<RecordInfoEntity>()
        list.add(RecordInfoEntity(UUID.randomUUID(),login!!,password!!,"test",UUID.randomUUID()))
        adapter.list = list

        //vm = ViewModelProvider(this,vmFactory) [LoginViewModel::class.java]
        vm = ViewModelProvider(this)[AccountInfoViewModel::class.java]
    }

    override fun onStop() {
        super.onStop()
    }
}