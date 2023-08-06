package com.screens.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.BreakingNewsAdapterBinding
import com.model.NewsResponse
import javax.inject.Inject

class BreakingNewsAdapter @Inject constructor() :
    PagingDataAdapter<NewsResponse.Article, BreakingNewsAdapter.Items>(COMPARATOR) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Items {
        val bind = BreakingNewsAdapterBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return Items(bind)
    }

    override fun onBindViewHolder(items: Items, i: Int) {
        items.binding.newsItem = getItem(i)
    }

//    override fun getItemCount(): Int {
//        return newsLis.size
//    }

    class Items(binding: BreakingNewsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: BreakingNewsAdapterBinding = binding
    }

    fun submitList(newsList: PagingData<NewsResponse.Article>) {
//        newsLis.addAll(newsList)
//        notifyDataSetChanged()
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<NewsResponse.Article>() {
            override fun areItemsTheSame(
                oldItem: NewsResponse.Article,
                newItem: NewsResponse.Article
            ): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(
                oldItem: NewsResponse.Article,
                newItem: NewsResponse.Article
            ): Boolean =
                oldItem == newItem

        }
    }

}


