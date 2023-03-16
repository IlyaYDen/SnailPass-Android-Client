package com.example.snailpasswordmanager.presentation.accountInfo

import android.R
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.StrictMath.abs
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
        supportActionBar?.title = "Account"// + intent.getSerializableExtra("ID")



        bindingClass.apply {

            rv.layoutManager = LinearLayoutManager(this@AccountInfoActivity)
            rv.adapter = adapter

        }
        (applicationContext as PasswordApp).appComponent.inject(this)

        vm = ViewModelProvider(this,vmFactory)[AccountInfoViewModel::class.java]


        vm.responce.onEach {
            if(it) {
                finish()
            }
        }.launchIn(lifecycleScope)

        if(intent.getBooleanExtra("MODE",false)) {

            id = intent.getSerializableExtra("ID") as UUID
            val service = intent.getStringExtra("SERVICE")
            val login = intent.getStringExtra("LOGIN")
            val password = intent.getStringExtra("PASSWORD")


            bindingClass.buttonDelete.setOnClickListener {

                vm.deletePassword(id)
                //finish()
            }

            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "Service",
                    value = service!!,
                    //nonce = "-",
                    record_id = id
                )
            )
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "Login",
                    value = login!!,
                    //nonce = "-",
                    record_id = id
                )
            )
            list.add(
                RecordAddFieldEntity(
                    id = UUID.randomUUID(),
                    name = "Password",
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
                    adapter.addList(it,0)
                    list.addAll(it)
                } //-else
                    //-Log.d("test", "emplty")
            }.launchIn(lifecycleScope)

            val listE = ArrayList<Pair<RecordAddFieldEntity,Int>>()
            for(i in list){
                listE.add(Pair(i,0))
            }
            adapter.list = listE    //c387b555-a5ff-4c93-a21e-7c7fc8049d78
                                    //dtE4xDnkrveKzCXJShVhMA==:endCVDRJZERxT0FpcDBaRg

            bindingClass.buttonDelete.visibility = View.VISIBLE




            bindingClass.buttonSave.setOnClickListener {
                //-Log.d("teat","--------1------")
                if(!adapter.canSave) {return@setOnClickListener}
                vm.editPassword(
                    passwordEntity = RecordEntity(
                        id = id,
                        name = adapter.list.get(0).first.value,
                        login = adapter.list.get(1).first.value,
                        userId = id.toString(),
                        isfavorite = false,
                        encrypted_password = adapter.list.get(2).first.value,
                        editedTime = "",
                        creationTime = ""
                        //name = bindingClass.editTextService.text.toString(),
                    )
                )


                if(adapter.list.size>2) {
                    val newList =adapter.list.subList(3,adapter.list.size)

                    val addList = ArrayList<RecordAddFieldEntity>()
                    val editedList =  ArrayList<RecordAddFieldEntity>()
                    val deletedList = ArrayList<UUID>()

                    newList.forEach {
                        val n = it.second
                        val item = it.first
                        if(n==1) addList.add(item)
                        if(n==2) deletedList.add(item.id)
                        if(n==3) editedList.add(item)
                    }
                    if(addList.isNotEmpty()) vm.addFields(addList)
                    if(editedList.isNotEmpty()) vm.editFields(editedList)
                    if(deletedList.isNotEmpty()) vm.deleteFields(deletedList)
                }
            }

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

            val listE = ArrayList<Pair<RecordAddFieldEntity,Int>>()
            for(i in list){
                listE.add(Pair(i,0))
            }
            adapter.list = listE
        //adapter.list = list


            bindingClass.buttonSave.setOnClickListener {
                //-Log.d("teat","--------1------")

                //if(adapter.list.get(0).first.value.isEmpty())

                if(!adapter.canSave) {return@setOnClickListener}


                vm.addPassword(
                    passwordEntity = RecordEntity(
                        id = id,
                        name = adapter.list.get(0).first.value,
                        login = adapter.list.get(1).first.value,
                        userId = id.toString(),
                        isfavorite = false,
                        encrypted_password = adapter.list.get(2).first.value,
                        editedTime = "",
                        creationTime = ""
                        //name = bindingClass.editTextService.text.toString(),
                    )
                )


                if(adapter.list.size>2) {
                    val newList =adapter.list.subList(3,adapter.list.size)

                    val addList = ArrayList<RecordAddFieldEntity>()

                    newList.forEach {
                        val item = it.first
                        addList.add(item)
                    }
                    if(addList.isNotEmpty()) vm.addFields(addList)
                }
            }
        }//ttt@ttt.ttt1

        bindingClass.ButtonAddField.setOnClickListener {
            //currentFocus?.clearFocus()
            val idt = UUID.randomUUID()
            list.add(
                RecordAddFieldEntity(
                    id = idt,
                    name = "",
                    value = "",
                    record_id = id
                )
            )
            //adapter.list = list
            adapter.addList(RecordAddFieldEntity(
                id = idt,
                name = "",
                value = "",
                record_id = id
            ))

        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // Not used in this example
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (shouldAllowDeletion(position)) {
                    val deletedItem = list[position]
                    //adapter.list.removeAt(position)
                    adapter.deleteItem(position)
                    //list.remove(deletedItem)

                    //adapter.notifyItemRemoved(position)

                    val snackbar = Snackbar.make(
                        bindingClass.rv,
                        "Item deleted",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.setAction("Undo") {
                        //adapter.list.add(position, deletedItem)
                        adapter.undoDelete()
                        //list.add(position,deletedItem)
                        //adapter.notifyItemInserted(position)

                        // Animate the view to its original position
                        val itemView = viewHolder.itemView
                        ObjectAnimator.ofFloat(itemView, "translationX", 0f)
                            .apply {
                                addListener(object : AnimatorListenerAdapter() {
                                    override fun onAnimationEnd(animation: Animator) {
                                        super.onAnimationEnd(animation)
                                        // Notify the adapter of the swipe cancellation
                                        adapter.notifyItemChanged(position)
                                    }
                                })
                                start()
                            }
                    }
                    snackbar.show()
                } else {
                    // Animate the view to return back to its original position
                    val itemView = viewHolder.itemView
                    ObjectAnimator.ofFloat(itemView, "translationX", 0f)
                        .apply {
                            addListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator) {
                                    super.onAnimationEnd(animation)
                                    // Notify the adapter of the swipe cancellation
                                    adapter.notifyItemChanged(position)
                                }
                            })
                            start()
                        }
                }
            }

            private fun shouldAllowDeletion(position: Int): Boolean = position > 2

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    // Calculate the new position of the view
                    val itemView = viewHolder.itemView
                    val originalTranslationX = itemView.translationX
                    val newTranslationX = dX.coerceIn(-itemView.width.toFloat(), 0f)
                    itemView.translationX = newTranslationX

                    // Calculate the alpha of the view based on its position
                    val alpha = 1f - abs(newTranslationX) / itemView.width
                    itemView.alpha = alpha

                    // Draw the view at the new position
                    c.translate(originalTranslationX, 0f)
                    viewHolder.itemView.draw(c)
                    c.translate(-originalTranslationX, 0f)
                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
            }
        })


        itemTouchHelper.attachToRecyclerView(bindingClass.rv)

        //adapter.filter.filter("")

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

