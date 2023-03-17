package com.example.snailpasswordmanager.presentation.noteList

import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.FragmentNotesBinding
import com.example.snailpasswordmanager.presentation.mainActivity.AppComponentProvider
import com.example.snailpasswordmanager.presentation.noteActivity.NoteActivity
import com.example.snailpasswordmanager.presentation.recordList.RecordListAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NoteListFragment : Fragment() {


    private val appComponent by lazy {
        (requireActivity() as AppComponentProvider).appComponent
    }

    lateinit var bindingClass: FragmentNotesBinding
    lateinit var viewModel: NoteListViewModel
    @Inject
    lateinit var vmFactory: NoteListViewModelFactory


    @Inject
    lateinit var connectivityManager: ConnectivityManager
    var networkConnection = false

    private val adapter: NoteListAdapter = NoteListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return bindingClass.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
        val toolbar : TextView? = activity?.findViewById(R.id.nameToolbar)
        if(toolbar!= null) {
            toolbar.text = getString(R.string.NotesToolBarText)
        }
        searchFun()
    }
    private var lastRefreshTime: Long = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        bindingClass = FragmentNotesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, vmFactory)[NoteListViewModel::class.java]



        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkConnection = true

                activity?.runOnUiThread {
                    bindingClass.ButtonRefreshNotes.isEnabled = true // or false
                    bindingClass.ButtonAddNotes.isEnabled = true // or false
                }
                Log.d("internet", "onAvailable: $network")
                viewModel.getNotes()
            }
            override fun onLost(network: Network) {
                super.onLost(network)
                networkConnection = false
                activity?.runOnUiThread {
                    bindingClass.ButtonRefreshNotes.isEnabled = false // or false
                    bindingClass.ButtonAddNotes.isEnabled = false // or false
                }
                Log.d("internet", "onLost: $network")
                //viewModel.getAddFields()
            }
        })
        if(!networkConnection){
            bindingClass.ButtonRefreshNotes.isEnabled = false // or false
            bindingClass.ButtonAddNotes.isEnabled = false // or false
        }

        bindingClass.ButtonRefreshNotes.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            val threshold = 5000 // 5 seconds
            if (currentTime - lastRefreshTime > threshold) {
                viewModel.getNotes()
                lastRefreshTime = System.currentTimeMillis()
            }
        }
        init()

        bindingClass.ButtonAddNotes.setOnClickListener{
            //launcher?.launch(Intent(this,AccountInfoActivity::class.java))
            val intent = Intent(activity, NoteActivity::class.java)
            startActivity(intent)
        }

        viewModel.noteListEdited.onEach {
            adapter.setNotes(viewModel.noteListEdited.value)
        }.launchIn(lifecycleScope)
    }

    private fun init() {
        bindingClass.apply {
            rcViewNotes.layoutManager = LinearLayoutManager(activity)
            rcViewNotes.adapter = adapter

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
}