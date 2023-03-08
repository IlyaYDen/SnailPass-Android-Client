package com.example.snailpasswordmanager.presentation.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.util.Log
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.RecordListItemBinding
import com.example.snailpasswordmanager.domain.model.RecordEntity
import com.example.snailpasswordmanager.presentation.accountInfo.AccountInfoActivity
import java.util.*
import kotlin.collections.ArrayList

class PasswordListAdapter: RecyclerView.Adapter<PasswordListAdapter.PasswordItemViewHolder>(),
    Filterable {

    var list = ArrayList<RecordEntity>()
    var listSearch = ArrayList<RecordEntity>()
    fun setPasswords(li: List<RecordEntity>) {
        list.clear()
        list.addAll(li)
        listSearch.clear()
        listSearch.addAll(li)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordItemViewHolder {
        //TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.record_list_item,
            parent,
            false
        )
        return PasswordItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PasswordItemViewHolder, position: Int) {
        //TODO("Not yet implemented")
        holder.bind(listSearch.get(position))

        holder.itemView.setOnClickListener {


            val intent = Intent(holder.itemView.context, AccountInfoActivity::class.java).apply {
                putExtra("MODE", true)
                putExtra("SERVICE", listSearch.get(position).name)
                putExtra("LOGIN", listSearch.get(position).login)
                putExtra("PASSWORD", listSearch.get(position).encrypted_password)
                putExtra("ID", listSearch.get(position).id)
            }

            holder.itemView.context.startActivity(intent)

/*
            val intent = Intent(holder.itemView.context, PasswordItemActivity::class.java).apply {
                putExtra("MODE", true)
                putExtra("SERVICE", list.get(position).name)
                putExtra("LOGIN", list.get(position).login)
                putExtra("PASSWORD", list.get(position).encrypted_password)
                putExtra("ID", list.get(position).id)
            }

            holder.itemView.context.startActivity(intent)*/
            //val intent = Intent(this, RegistrationActivity::class.java).apply {

            }

    }


    override fun getItemCount(): Int {
        return listSearch.size
        //TODO("Not yet implemented")
    }




    class PasswordItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = RecordListItemBinding.bind(view)
        fun bind(passwordEntity: RecordEntity) = with(binding){
            serviceName.text = passwordEntity.name
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault())


                val filteredList = if (query.isNullOrEmpty()) {
                    list
                } else {
                    Log.d("test",query)
                    list.filter {
                        it.name.lowercase(Locale.getDefault()).contains(query)
                        //it.toLowerCase(Locale.getDefault()).contains(query)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                listSearch = results?.values as ArrayList<RecordEntity>
                notifyDataSetChanged()
            }
        }
    }

}