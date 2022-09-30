package com.example.snailpasswordmanager.presentation.mainscreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.PasswordItemBinding
import com.example.snailpasswordmanager.domain.model.PasswordEntity
import com.example.snailpasswordmanager.presentation.passworditem.PasswordItemActivity

class PasswordListAdapter: RecyclerView.Adapter<PasswordListAdapter.PasswordItemViewHolder>() {

    var list = ArrayList<PasswordEntity>()
    set(value) {
        field.clear()
        field.addAll(value)
        notifyDataSetChanged()
    }

    fun setPasswords(li: List<PasswordEntity>) {
        list.clear()
        list.addAll(li)
        notifyDataSetChanged()
    }

    fun addPassword(passwordEntity : PasswordEntity){
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

            val intent = Intent(holder.itemView.context, PasswordItemActivity::class.java).apply {
                putExtra("MODE", true)
                putExtra("SERVICE", list.get(position).service)
                putExtra("LOGIN", list.get(position).login)
                putExtra("PASSWORD", list.get(position).password)
                putExtra("ID", list.get(position).id)
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