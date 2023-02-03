package com.example.snailpasswordmanager.presentation.accountInfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.ActivityAccountInfoListBinding
import com.example.snailpasswordmanager.databinding.RecordListItemBinding
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.domain.model.RecordInfoEntity

class AccountInfoAdapter : RecyclerView.Adapter<AccountInfoAdapter.AccountItemViewHolder>() {

    //var list = listOf<RecordInfoEntity>()
    var list = ArrayList<RecordInfoEntity>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_info_list_item, parent, false)


        return AccountItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {

        val accountItem = list[position]
        holder.key.text = accountItem.field_name
        holder.value.text = accountItem.value
        holder.itemView.setOnClickListener{
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }



    class AccountItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val key = view.findViewById<TextView>(R.id.textView1)
        val value = view.findViewById<TextView>(R.id.textView2)
    }
}