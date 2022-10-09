package com.example.snailpasswordmanager.presentation.mainscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.databinding.ActivityMainListBinding
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.presentation.passworditem.PasswordItemActivity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


//@AndroidEntryPoint
class MainListActivity @Inject constructor(
        ) : AppCompatActivity() {

    lateinit var bindingClass : ActivityMainListBinding

    private var launcher: ActivityResultLauncher<Intent>? = null
    @Inject
    lateinit var vmFactory: MainListViewModelFactory

    private lateinit var viewModel : MainListViewModel
    private val adapter :PasswordListAdapter = PasswordListAdapter()

    override fun onResume() {
        super.onResume()

        lifecycleScope.launch() {
            viewModel.getPasswords().collect() {
                adapter.setPasswords(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingClass = ActivityMainListBinding.inflate(layoutInflater)

        (applicationContext as PasswordApp).appComponent.inject(this)

        setContentView(bindingClass.root)


        viewModel = ViewModelProvider(this,vmFactory)
            .get(MainListViewModel::class.java)

        init()

        lifecycleScope.launch() {
            viewModel.getPasswords().collect() {
                adapter.setPasswords(it)
            }
        }



        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->

        }

        bindingClass.ButtonAdd.setOnClickListener {



            launcher?.launch(Intent(this,PasswordItemActivity::class.java))
            //val intent = Intent(this, PasswordItemActivity::class.java).apply {
            //    putExtra("NEW", true)
            //}
//
            //startActivity(intent)

        }
    }
    private fun init() {
        bindingClass.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainListActivity)
            rcView.adapter = adapter

        }
    }


    /*fun passwordAddButton(view: View) {

        val intent = Intent(this, RegistrationActivity::class.java).apply {
            //putExtra("EXTRA_MESSAGE", "")
        }
        startActivity(intent)

    }*/
}
