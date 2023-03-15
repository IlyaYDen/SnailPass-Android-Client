package com.example.snailpasswordmanager.presentation.recordList

import android.content.Intent
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
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var vmFactory: MainListViewModelFactory

    lateinit var masterHash: String


    private lateinit var viewModel: MainListViewModel
    private val adapter: RecordListAdapter = RecordListAdapter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return bindingClass.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        viewModel.getPasswords()
        viewModel.getAddFields()

        val toolbar : TextView? = activity?.findViewById(R.id.nameToolbar)
        if(toolbar!= null) {
            toolbar.text = getString(R.string.AccountsToolBarText)
        }

        searchFun()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        bindingClass = FragmentRecordListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, vmFactory)[MainListViewModel::class.java]


        init()
//ttt@ttt.ttt1

        viewModel.passwordListEdited.onEach {
            adapter.setPasswords(viewModel.passwordListEdited.value)
        }.launchIn(lifecycleScope)


        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                if (!adapter.list.isEmpty()) {

                    bindingClass.linearLayout.visibility = View.GONE
                } else {
                    bindingClass.linearLayout.visibility = View.VISIBLE

                }
            }
        })


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
        bindingClass.ButtonRefresh.setOnClickListener {//todo make timer to disable refresh-spam

            viewModel.getPasswords()
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