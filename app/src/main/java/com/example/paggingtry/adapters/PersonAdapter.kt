package com.example.paggingtry.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paggingtry.databinding.ItemPersonBinding
import com.example.paggingtry.models.Person

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    private val persons = ArrayList<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonBinding.inflate(inflater, parent, false)

        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = persons[position]
        with(holder.binding) {
            tvName.text = person.name
            tvSpecies.text = person.species

            Glide.with(holder.itemView.context)
                .load(person.image)
                .circleCrop()
                .into(ivAvatar)
        }

    }

    override fun getItemCount(): Int = persons.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<Person>) {
        persons.clear()
        persons.addAll(list)
        notifyDataSetChanged()
    }

}