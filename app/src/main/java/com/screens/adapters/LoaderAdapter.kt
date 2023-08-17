package com.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.LoaderIteamBinding
import javax.inject.Inject

class LoaderAdapter @Inject constructor() : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {

        val bind = LoaderIteamBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoaderViewHolder(bind)
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoaderViewHolder(private val binding: LoaderIteamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.loadingData.isVisible = loadState is LoadState.Loading

        }
    }

}