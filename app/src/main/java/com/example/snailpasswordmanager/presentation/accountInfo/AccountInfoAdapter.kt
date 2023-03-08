package com.example.snailpasswordmanager.presentation.accountInfo

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.google.android.material.textfield.TextInputLayout
import kotlin.collections.ArrayList

class AccountInfoAdapter : RecyclerView.Adapter<AccountInfoAdapter.AccountItemViewHolder>() {

    //var list = listOf<RecordInfoEntity>()
    var textboxEditable: Boolean = true
    var list = mutableListOf<RecordAddFieldEntity>()
        set(value) {
            textboxEditable = false
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
            textboxEditable = true
        }

    fun addList(value: RecordAddFieldEntity){
        textboxEditable = false
        list.add(value)
        notifyDataSetChanged()
        textboxEditable = true
    }
    fun addList(value: List<RecordAddFieldEntity>){
        textboxEditable = false
        list.addAll(value)
        notifyDataSetChanged()
        textboxEditable = true
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountItemViewHolder {
        val view =
        if(viewType==0)
            LayoutInflater.from(parent.context).inflate(R.layout.edit_record_info_list_item, parent, false)
        else if(viewType==1)
            LayoutInflater.from(parent.context).inflate(R.layout.record_info_list_item, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.record_info_list_item_password, parent, false)


        return AccountItemViewHolder(view)
    }
/*
    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {

        val accountItem = list[position]
        holder.key.text = accountItem.name
        holder.value.text = accountItem.value
    }*/

    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {
        val accountItem = list[position]


        holder.key.text = accountItem.name
        holder.value.text = accountItem.value
        holder.value.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                accountItem.value = s.toString()
            }
        })
    }
    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {

        return if (position>2) 0 else if(position == 2) 2 else 1//super.getItemViewType(position)
    }
    class AccountItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val key = view.findViewById<TextView>(R.id.textView1)
        val value = view.findViewById<TextView>(R.id.textView2)
        val valueView = view.findViewById<TextInputLayout>(R.id.username_text_input_layout)
    }


}

