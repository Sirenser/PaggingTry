package com.example.paggingtry.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paggingtry.databinding.ItemPersonBinding
import com.example.paggingtry.models.Person

class PersonPagingAdapter
    : PagingDataAdapter<Person, PersonPagingAdapter.PersonViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)!!

        with(holder.binding) {
            tvName.text = person.name
            tvSpecies.text = person.species

            Glide.with(holder.itemView.context)
                .load(person.image)
                .circleCrop()
                .into(ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPersonBinding.inflate(inflater, parent, false)

        return PersonViewHolder(binding)
    }

    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }
        }
    }

}