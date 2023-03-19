package com.example.paggingtry.adapters

import android.service.media.MediaBrowserService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paggingtry.databinding.ItemErrorBinding
import com.example.paggingtry.databinding.ItemProgressBinding

class PersonsLoaderStateAdapter : LoadStateAdapter<PersonsLoaderStateAdapter.ItemViewHolder>() {


    override fun getStateViewType(loadState: LoadState): Int = when (loadState) {
        is LoadState.Error -> 1
        is LoadState.Loading -> 0
        is LoadState.NotLoading -> error("Not supported")
    }


    override fun onBindViewHolder(
        holder: ItemViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)
            : ItemViewHolder {
        return when (loadState) {
            LoadState.Loading -> ProgressViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.Error -> ErrorViewHolder(LayoutInflater.from(parent.context), parent)
            is LoadState.NotLoading -> error("Not supported")

        }
    }

    abstract class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(loadState: LoadState)
    }

    class ProgressViewHolder internal constructor(
        private val binding: ItemProgressBinding
    ) : ItemViewHolder(binding.root) {
        override fun bind(loadState: LoadState) {
        }

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ProgressViewHolder {
                return ProgressViewHolder(
                    ItemProgressBinding.inflate(layoutInflater, parent, attachToRoot)
                )
            }

        }
    }

    class ErrorViewHolder internal constructor(
        private val binding: ItemErrorBinding
    ) : ItemViewHolder(binding.root) {
        override fun bind(loadState: LoadState) {
            require(loadState is LoadState.Error)
            binding.errorMessage.text = loadState.error.localizedMessage
        }

        companion object {
            operator fun invoke(
                layoutInflater: LayoutInflater,
                parent: ViewGroup? = null,
                attachToRoot: Boolean = false
            ): ProgressViewHolder {
                return ProgressViewHolder(
                    ItemProgressBinding.inflate(layoutInflater, parent, attachToRoot)
                )
            }

        }
    }


}