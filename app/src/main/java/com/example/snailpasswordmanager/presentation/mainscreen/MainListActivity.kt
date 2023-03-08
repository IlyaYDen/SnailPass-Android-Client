package com.example.snailpasswordmanager.presentation.mainscreen

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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


        //menuInflater.inflate(R.menu.menu_main, menu)

        val searchView = bindingClass.searchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Do something when the search view gains focus
                Log.d("test", "Search view gained focus")
            }
        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("test","testttt")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("test","testttt2")
                adapter.filter.filter(newText)
                return true
            }

        })
        searchView.setQuery("", false) // Set a default query

        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = /*ActivityMainListBinding*/ActivityRecordListBinding.inflate(layoutInflater)

        (applicationContext as PasswordApp).appComponent.inject(this)

        setContentView(bindingClass.root)


        viewModel = ViewModelProvider(this,vmFactory)
            .get(MainListViewModel::class.java)

        init()

        val drawerLayout : DrawerLayout = bindingClass.drawerLayout
        val menuImage = bindingClass.imageMenu
        menuImage.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
            }

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


        //val toolbar: LinearLayout = bindingClass.toolbar
        //setSupportActionBar(toolbar)
        //supportActionBar?.title = "Accounts"

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
        searchFun()
    }

    //dddd@ddd.ddd
    private fun searchFun() {
        val searchView = bindingClass.searchView
        searchView.queryHint = "Search"
        searchView.setOnCloseListener{
            bindingClass.nameToolbar.visibility = View.VISIBLE
            return@setOnCloseListener false
        }

        searchView.setOnSearchClickListener {
            bindingClass.nameToolbar.visibility = View.GONE

        }
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }

        })
        searchView.setQuery("", false) // Set a default query
    }

    private fun init() {
        bindingClass.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainListActivity)
            rcView.adapter = adapter

        }
    }

}