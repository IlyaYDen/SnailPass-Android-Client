package com.example.snailpasswordmanager.presentation.accountInfo

import android.R
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.PasswordApp
import com.example.snailpasswordmanager.databinding.ActivityAccountInfoListBinding
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject


class AccountInfoActivity: AppCompatActivity() {


    val list = ArrayList<RecordAddFieldEntity>()

    lateinit var bindingClass : ActivityAccountInfoListBinding

    private lateinit var vm : AccountInfoViewModel

    private val adapter : AccountInfoAdapter = AccountInfoAdapter()

    @Inject
    lateinit var vmFactory: AccountInfoModelFactory

    lateinit var id: UUID
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_account_info_list)x

        bindingClass = /*ActivityMainListBinding*/ActivityAccountInfoListBinding.inflate(layoutInflater)

        setContentView(bindingClass.root)
        val toolbar: Toolbar = bindingClass.toolbar2
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Account"


        bindingClass.apply {

            rv.layoutManager = LinearLayoutManager(this@AccountInfoActivity)
            rv.adapter = adapter

        }
        (applicationContext as PasswordApp).appComponent.inject(this)

        vm = ViewModelProvider(this,vmFactory)[AccountInfoViewModel::class.java]


        if(intent.getBooleanExtra("MODE",false)) {

            id = intent.getSerializableExtra("ID") as UUID
            val service = intent.getStringExtra("SERVICE")
            val login = intent.getStringExtra("LOGIN")
            val password = intent.getStringExtra("PASSWORD")


            bindingClass.buttonDelete.setOnClickListener {

                vm.deletePassword(id)
                finish()
            }
            //val list = ArrayList<RecordInfoEntity>()
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "service",
                    value = service!!,
                    //nonce = "-",
                    record_id = id
                )
            )
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "login",
                    value = login!!,
                    //nonce = "-",
                    record_id = id
                )
            )
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "password",
                    value = password!!,
                    //nonce = "-",
                    record_id = id
                )
            )
            vm.getAddFields(id) //id

            vm.fieldListEdited.onEach {
                if(!it.isEmpty()) {
                    //-Log.d("test", it[0].value)
                    //-Log.d("test", it[0].name)
                    adapter.addList(it)
                    list.addAll(it)
                } //-else
                    //-Log.d("test", "emplty")
            }.launchIn(lifecycleScope)


            adapter.list = list

            bindingClass.buttonDelete.visibility = View.VISIBLE


            bindingClass.buttonSave.setOnClickListener { //todo if eny fields  is empty
                //-Log.d("teat","--------1------")
                if(adapter.list.size>2) {
                    vm.editPassword(
                        passwordEntity = RecordEntity(
                            id = id,
                            name = adapter.list.get(0).value,
                            login = adapter.list.get(1).value,
                            userId = id.toString(),
                            isfavorite = false,
                            encrypted_password = adapter.list.get(2).value,
                            editedTime = "",
                            creationTime = ""
                            //name = bindingClass.editTextService.text.toString(),
                        ),
                        adapter.list.subList(3, adapter.list.size)
                    )
                }
                else

                    vm.editPassword(
                        passwordEntity = RecordEntity(
                            id = id,
                            name = adapter.list.get(0).value,
                            login = adapter.list.get(1).value,
                            userId = id.toString(),
                            isfavorite = false,
                            encrypted_password = adapter.list.get(2).value,
                            editedTime = "",
                            creationTime = ""
                            //name = bindingClass.editTextService.text.toString(),
                        )
                    )
                //bindingClass.rv.
                finish()
            }
            //vm = ViewModelProvider(this,vmFactory) [LoginViewModel::class.java]
        }
        else {
            id = UUID.randomUUID()


            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "service",
                    value = "",
                    //nonce = "-",
                    record_id = id
                )
            )
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "login",
                    value = "",
                    //nonce = "-",
                    record_id = id
                )
            )
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "password",
                    value = "",
                    //nonce = "-",
                    record_id = id
                )
            )
            adapter.list = list

            bindingClass.buttonSave.setOnClickListener {

                //-Log.d("teat","--------2------")
                if(adapter.list.size>2) {
                    vm.addPassword(
                        passwordEntity = RecordEntity(
                            id = id,
                            name = adapter.list.get(0).value,
                            login = adapter.list.get(1).value,
                            userId = id.toString(),
                            isfavorite = false,
                            encrypted_password = adapter.list.get(2).value,
                            editedTime = "",
                            creationTime = ""
                            //name = bindingClass.editTextService.text.toString(),
                        ),
                        adapter.list.subList(3, adapter.list.size)
                    )
                }
                else

                    vm.addPassword(
                        passwordEntity = RecordEntity(
                            id = id,
                            name = adapter.list.get(0).value,
                            login = adapter.list.get(1).value,
                            userId = id.toString(),
                            isfavorite = false,
                            encrypted_password = adapter.list.get(2).value,
                            editedTime = "",
                            creationTime = ""
                            //name = bindingClass.editTextService.text.toString(),
                        )
                    )
                //bindingClass.rv.
                finish()
            }
        }
        //-Log.d("test----",list.size.toString())
        //list.addAll(adapter.list.subList(3, adapter.list.size))
        bindingClass.ButtonAddField.setOnClickListener {
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "",
                    value = "",
                    record_id = id
                )
            )
            //adapter.list = list
            adapter.addList(RecordAddFieldEntity(
                id = UUID.randomUUID(),
                name = "",
                value = "",
                record_id = id
            ))
        }

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}

