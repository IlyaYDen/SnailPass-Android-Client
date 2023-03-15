package com.example.snailpasswordmanager.presentation.noteList

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.presentation.noteActivity.NoteActivity
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.databinding.NoteListItemBinding
import com.example.snailpasswordmanager.domain.model.NoteEntity
import java.util.*
import kotlin.collections.ArrayList

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteItemViewHolder>(),
    Filterable {

    var list = ArrayList<NoteEntity>()
    var listSearch = ArrayList<NoteEntity>()

    fun setNotes(li: List<NoteEntity>) {
        list.clear()
        list.addAll(li)
        listSearch.clear()
        listSearch.addAll(li)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_list_item, parent,false)
        return NoteItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: NoteItemViewHolder, position: Int) {
        holder.bind(listSearch.get(position))

        holder.itemView.setOnClickListener {


            val intent = Intent(holder.itemView.context, NoteActivity::class.java).apply {
                putExtra("MODE", true)
                putExtra("ID", listSearch.get(position).id)
                putExtra("NAME", listSearch.get(position).name)
                putExtra("CONTENT", listSearch.get(position).content)
            }

            holder.itemView.context.startActivity(intent)

        }

    }



    override fun getItemCount(): Int {
        return listSearch.size
        //TODO("Not yet implemented")
    }




    class NoteItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = NoteListItemBinding.bind(view)
        fun bind(noteEntity: NoteEntity) = with(binding){
            NoteName.text = noteEntity.name
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint?.toString()?.lowercase(Locale.getDefault())


                val filteredList = if (query.isNullOrEmpty()) {
                    list
                } else {
                    //-Log.d("test",query)
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
                listSearch = results?.values as ArrayList<NoteEntity> //todo
                notifyDataSetChanged()
            }
        }
    }

}