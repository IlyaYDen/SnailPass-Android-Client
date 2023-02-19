package com.example.snailpasswordmanager.presentation.mainscreen

import android.accounts.Account
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.databinding.ActivityRecordListBinding
import com.example.snailpasswordmanager.domain.usecase.passwords.PasswordUseCases
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


//@AndroidEntryPoint
class MainListActivity @Inject constructor(
        ) : AppCompatActivity() {

    lateinit var bindingClass : ActivityRecordListBinding
    private var launcher: ActivityResultLauncher<Intent>? = null
    @Inject
    lateinit var vmFactory: MainListViewModelFactory

    lateinit var masterHash : String

    private lateinit var viewModel : MainListViewModel
    private val adapter :PasswordListAdapter = PasswordListAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        //viewModel.onEvent(event = PasswordsEvent.GetPasswordsList)
        //lifecycleScope.launch {
        //    viewModel.getPasswords().collect {
        //        adapter.setPasswords(it)
        //    }
        //}
        viewModel.getPasswords()
        viewModel.getAddFields()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = /*ActivityMainListBinding*/ActivityRecordListBinding.inflate(layoutInflater)

        (applicationContext as PasswordApp).appComponent.inject(this)

        setContentView(bindingClass.root)


        viewModel = ViewModelProvider(this,vmFactory)
            .get(MainListViewModel::class.java)

        init()
        //masterHash =
        //    intent.getStringExtra("MASTER_HASH").toString() // TODO

        viewModel.passwordListEdited.onEach {
            adapter.setPasswords(viewModel.passwordListEdited.value)
        }.launchIn(lifecycleScope)



        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //result: ActivityResult ->

        }

        bindingClass.ButtonAdd.setOnClickListener {


            //launcher?.launch(Intent(this,AccountInfoActivity::class.java))
            val intent = Intent(this, AccountInfoActivity::class.java).apply {
                putExtra("NEW", true)
                putExtra("MODE", false)

            }
            startActivity(intent)
            /*
            launcher?.launch(Intent(this,PasswordItemActivity::class.java))
            val intent = Intent(this, PasswordItemActivity::class.java).apply {
                putExtra("NEW", true)
            }
            startActivity(intent)*/

        }
    }
    private fun init() {
        bindingClass.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainListActivity)
            rcView.adapter = adapter

        }
    }

}