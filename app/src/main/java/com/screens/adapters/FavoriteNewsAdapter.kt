package com.screens.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.BreakingNewsAdapterBinding
import com.example.myapplication.databinding.FavNewsAdapterBinding
import com.model.NewsResponse
import com.utlis.Comparator
import com.utlis.callbackinterface.setonItemClick
import javax.inject.Inject

class FavoriteNewsAdapter @Inject constructor(
    private val context: Context
) : ListAdapter<NewsResponse.Article, FavoriteNewsAdapter.Items>(Comparator.COMPARATOR) {
    lateinit var _setonItemClick: setonItemClick

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): Items {
        val bind = FavNewsAdapterBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return Items(bind)
    }


    override fun onBindViewHolder(items: Items, i: Int) {
        items.binding.newsItem = getItem(i)
        items.binding.favoriteNewsAdapter = this

    }

    fun onItemClick(article: NewsResponse.Article) {
        _setonItemClick.onNewsItemClicked(article, context)
    }


    class Items(binding: FavNewsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: FavNewsAdapterBinding = binding
    }

    fun setItemClickListener(_setonclick: setonItemClick) {
        this._setonItemClick = _setonclick
    }


}

