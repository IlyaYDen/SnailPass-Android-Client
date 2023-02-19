package com.example.snailpasswordmanager.presentation.accountInfo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import kotlin.collections.ArrayList

class AccountInfoAdapter : RecyclerView.Adapter<AccountInfoAdapter.AccountItemViewHolder>() {

    //var list = listOf<RecordInfoEntity>()
    var list = ArrayList<RecordAddFieldEntity>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountItemViewHolder {
        val view =
        if(viewType==0)
            LayoutInflater.from(parent.context).inflate(R.layout.edit_record_info_list_item, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.record_info_list_item, parent, false)

        Log.d("test",viewType.toString());

        return AccountItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {

        val accountItem = list[position]
        holder.key.text = accountItem.field_name
        holder.value.text = accountItem.value
        holder.itemView.setOnClickListener{
            true
        }

        val editT1 = holder.key
        editT1.addTextChangedListener {
            list[position].field_name = editT1.text.toString()
        }
        val editT2 = holder.value
        editT2.addTextChangedListener {
            list[position].value = editT2.text.toString()
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {

        return if (position>2) 0 else 1//super.getItemViewType(position)
    }

    fun addList(value: List<RecordAddFieldEntity>) {
        list.addAll(value)
        notifyDataSetChanged()
    }

    class AccountItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val key = view.findViewById<TextView>(R.id.textView1)
        val value = view.findViewById<TextView>(R.id.textView2)
    }
}