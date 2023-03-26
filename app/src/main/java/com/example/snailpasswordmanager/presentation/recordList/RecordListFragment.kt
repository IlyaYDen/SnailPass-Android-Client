package com.example.snailpasswordmanager.presentation.recordList

import android.content.Intent
import android.net.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.FragmentRecordListBinding
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoActivity
import com.example.snailpasswordmanager.presentation.mainActivity.AppComponentProvider
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class RecordListFragment : Fragment() {

    private val appComponent by lazy {
        (requireActivity() as AppComponentProvider).appComponent
    }

    lateinit var bindingClass: FragmentRecordListBinding
    private var launcher: ActivityResultLauncher<Intent>? = null

    @Inject
    lateinit var vmFactory: RecordListViewModelFactory


    var active = true
    @Inject
    lateinit var connectivityManager: ConnectivityManager
    var networkConnection: Boolean = false
    // =
        //context?.getSystemService(
        //AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager



    private lateinit var viewModel: RecordListViewModel
    private val adapter: RecordListAdapter = RecordListAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return bindingClass.root
    }

    override fun onPause() {
        super.onPause()
        active = false
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        viewModel.getPasswords()
        //viewModel.getAddFields()
        active = true

        val toolbar : TextView? = activity?.findViewById(R.id.nameToolbar)
        if(toolbar!= null) {
            toolbar.text = getString(R.string.AccountsToolBarText)
        }

        searchFun()
    }
    private var lastRefreshTime: Long = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        bindingClass = FragmentRecordListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, vmFactory)[RecordListViewModel::class.java]


        init()
        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkConnection = true

                activity?.runOnUiThread {
                    bindingClass.ButtonAdd.isEnabled = true // or false
                    bindingClass.ButtonRefresh.isEnabled = true // or false
                }
                Log.d("internet", "onAvailable: $network")
                //if(active)
                viewModel.getPasswords()
            }
            override fun onLost(network: Network) {
                super.onLost(network)
                networkConnection = false
                activity?.runOnUiThread {
                    bindingClass.ButtonAdd.isEnabled = false
                    bindingClass.ButtonRefresh.isEnabled = false
                }
                Log.d("internet", "onLost: $network")
                //viewModel.getAddFields()
            }
        })
        if(!networkConnection){
            bindingClass.ButtonAdd.isEnabled = false
            bindingClass.ButtonRefresh.isEnabled = false
        }

        //if(loginMode == LoginMode.OFFLINE) bindingClass.ButtonAdd.isEnabled = false

        //viewModel.passwordListEdited.onEach {
        //    adapter.setPasswords(it)
//
        //    if (!it.isEmpty()) {
//
        //        bindingClass.linearLayout.visibility = View.GONE
        //    } else {
        //        bindingClass.linearLayout.visibility = View.VISIBLE
//
        //    }
        //}.launchIn(lifecycleScope)


        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            //result: ActivityResult ->

        }

        bindingClass.ButtonAdd.setOnClickListener {


            //launcher?.launch(Intent(this,AccountInfoActivity::class.java))
            val intent = Intent(activity, AccountInfoActivity::class.java).apply {
                putExtra("NEW", true)
                putExtra("MODE", false)

            }
            startActivity(intent)
            //-Log.d("test","test")

        }
        bindingClass.ButtonRefresh.setOnClickListener {

            val currentTime = System.currentTimeMillis()
            val threshold = 5000 // 5 seconds
            if (currentTime - lastRefreshTime > threshold) {

                viewModel.getPasswords()
                //viewModel.getAddFields()

                lastRefreshTime = System.currentTimeMillis()
            }
        }
    }

    private fun searchFun() {
        val searchView : SearchView? = activity?.findViewById(R.id.searchView) // bindingClass.searchView
        val nameToolbar : TextView? = activity?.findViewById(R.id.nameToolbar) // bindingClass.searchView

        if(searchView!=null && nameToolbar!=null) {
            searchView.queryHint = "Search"
            searchView.setOnCloseListener {
                Log.d("testsdfvdsf", "testfrefewr")//ttt@ttt.ttt1
                nameToolbar.visibility = View.VISIBLE
                return@setOnCloseListener false
            }

            searchView.setOnSearchClickListener {
                nameToolbar.visibility = View.GONE

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
    }
    private fun init() {
        bindingClass.apply {
            rcView.layoutManager = LinearLayoutManager(activity)
            rcView.adapter = adapter

        }
    }
}

    //dddd@ddd.ddd