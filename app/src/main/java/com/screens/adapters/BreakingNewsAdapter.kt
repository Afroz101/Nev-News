package com.screens.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.BreakingNewsAdapterBinding
import com.model.NewsResponse
import com.utlis.Comparator
import com.utlis.callbackinterface.setonItemClick
import javax.inject.Inject

class BreakingNewsAdapter @Inject constructor(
    private val contex: Context
) : PagingDataAdapter<NewsResponse.Article, BreakingNewsAdapter.Items>(Comparator.COMPARATOR) {

    lateinit var _setonItemClick: setonItemClick
//    private var

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
        items.binding.breakingNewsAdapter = this

    }

    fun onItemClick(article: NewsResponse.Article) {
        _setonItemClick.onNewsItemClicked(article, contex)
    }


    class Items(binding: BreakingNewsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: BreakingNewsAdapterBinding = binding
    }

    fun setItemClickListener(_setonclick: setonItemClick) {
        this._setonItemClick = _setonclick
    }


}

