package com.example.snailpasswordmanager.presentation.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.presentation.listItem.ListItemView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.PasswordItemBinding
import com.example.snailpasswordmanager.domain.model.PasswordEntity

class PasswordListAdapter: RecyclerView.Adapter<PasswordListAdapter.PasswordItemViewHolder>() {

    var list = ArrayList<PasswordEntity>()


    fun addPass(passwordEntity: PasswordEntity){
        list.add(passwordEntity)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordItemViewHolder {
        //TODO("Not yet implemented")
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.password_item,
            parent,
            false
        )
        return PasswordItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: PasswordItemViewHolder, position: Int) {
        //TODO("Not yet implemented")
        holder.bind(list.get(position))

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, ListItemView::class.java).apply {
                putExtra("SERVICE", list.get(position).service)
                putExtra("LOGIN", list.get(position).login)
                putExtra("PASSWORD", list.get(position).password)
            }

            holder.itemView.context.startActivity(intent)
            //val intent = Intent(this, RegistrationActivity::class.java).apply {

            }

    }


    override fun getItemCount(): Int {
        return list.size
        //TODO("Not yet implemented")
    }


    class PasswordItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = PasswordItemBinding.bind(view)
        fun bind(passwordEntity: PasswordEntity) = with(binding){
            serviceName.text = passwordEntity.service
        }
    }
}