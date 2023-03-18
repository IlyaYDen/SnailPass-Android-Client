package com.example.snailpasswordmanager.presentation.noteActivity

import android.net.ConnectivityManager
import android.net.Network
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityAccountInfoListBinding
import com.example.snailpasswordmanager.databinding.ActivityNoteBinding
import com.example.snailpasswordmanager.domain.model.NoteEntity
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoAdapter
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoModelFactory
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoViewModel
import com.example.snailpasswordmanager.presentation.noteList.NoteListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject


class NoteActivity : AppCompatActivity() {


    val list = ArrayList<RecordAddFieldEntity>()

    lateinit var bindingClass : ActivityNoteBinding

    private lateinit var vm : NoteViewModel

    @Inject
    lateinit var vmFactory: NoteViewModelFactory


    @Inject
    lateinit var connectivityManager: ConnectivityManager
    var networkConnection = false


    lateinit var nameN: String
    lateinit var contentN: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_note)

        bindingClass = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        (applicationContext as PasswordApp).appComponent.inject(this)

        val toolbar: Toolbar = bindingClass.toolbarNote
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Note"

        vm = ViewModelProvider(this, vmFactory)[NoteViewModel::class.java]


        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkConnection = true

                runOnUiThread {
                    bindingClass.buttonNoteDelete.isEnabled = true // or false
                    bindingClass.buttonNoteSave.isEnabled = true // or false
                }
                Log.d("internet", "onAvailable: $network")
            }
            override fun onLost(network: Network) {
                super.onLost(network)
                networkConnection = false
                runOnUiThread {
                    bindingClass.buttonNoteDelete.isEnabled = false // or false
                    bindingClass.buttonNoteSave.isEnabled = false // or false
                }
                Log.d("internet", "onLost: $network")
                //viewModel.getAddFields()
            }
        })
        if(!networkConnection){
            bindingClass.buttonNoteDelete.isEnabled = false // or false
            bindingClass.buttonNoteSave.isEnabled = false // or false
        }


        if(intent.getBooleanExtra("MODE",false)) {
            nameN = intent.getStringExtra("NAME").toString()
            contentN = intent.getStringExtra("CONTENT").toString()
            val id = intent.getStringExtra("ID").toString()



            bindingClass.noteName.setText(nameN)
            bindingClass.noteContent.setText(contentN)
            bindingClass.buttonNoteDelete.visibility = View.VISIBLE
            bindingClass.buttonNoteDelete.setOnClickListener {
                vm.deleteNote(id)
                //finish()
            }

            bindingClass.buttonNoteSave.text = getString(R.string.EditNote)

            bindingClass.buttonNoteSave.setOnClickListener {
                vm.editNote(NoteEntity(
                    name = bindingClass.noteName.text.toString(),
                    content = bindingClass.noteContent.text.toString(),
                    id = id,
                    is_favorite = false,
                    is_deleted = false,
                    creation_time = "",
                    update_time = "",
                    user_id = ""
                ))

                //finish()
            }
        }
        else{

            bindingClass.buttonNoteSave.setOnClickListener {
                if(bindingClass.noteName.text.toString().isNotEmpty() && bindingClass.noteContent.text.toString().isNotEmpty()) {
                    vm.insertNote(
                        NoteEntity(
                            name = bindingClass.noteName.text.toString(),
                            content = bindingClass.noteContent.text.toString(),
                            id = UUID.randomUUID().toString(),
                            is_favorite = false,
                            is_deleted = false,
                            creation_time = "",
                            update_time = "",
                            user_id = ""
                        )
                    )
                    //finish()
                }
                else {
                    if(bindingClass.noteName.text.toString().isNotEmpty())
                        bindingClass.noteName.error = getString(R.string.empty_error)
                    if(bindingClass.noteContent.text.toString().isNotEmpty())
                        bindingClass.noteContent.error = getString(R.string.empty_error)
                }
            }


        }

        vm.boolean.onEach {
            if(it)
                finish()
        }.launchIn(lifecycleScope)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}