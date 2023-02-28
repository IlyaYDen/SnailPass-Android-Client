package com.example.snailpasswordmanager.presentation.mainscreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityRecordListBinding
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Perform search
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Do something when the search text changes
                adapter.filter.filter(newText)
                return true
            }
        })
        searchView.setQuery("", false) // Set a default query

        return true// super.onCreateOptionsMenu(menu)
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


        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()

                if(!adapter.list.isEmpty()){
                    bindingClass.linearLayout.visibility = View.GONE
                }
                else {
                    bindingClass.linearLayout.visibility = View.VISIBLE

                }
            }
        })


        val toolbar: Toolbar = bindingClass.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Main List");

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