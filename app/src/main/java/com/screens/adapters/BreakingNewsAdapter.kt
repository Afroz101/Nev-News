package com.screens.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.BreakingNewsAdapterBinding
import com.model.NewsResponse
import com.screens.activity.NewsDetailActivity
import com.utlis.Comparator
import com.utlis.callbackinterface.setonItemClick
import javax.inject.Inject


class BreakingNewsAdapter @Inject constructor(
    private val _setonclick: setonItemClick,
    private val contex: Context
) :
    PagingDataAdapter<NewsResponse.Article, BreakingNewsAdapter.Items>(Comparator.COMPARATOR) {

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

    fun onItemClick(view: View, article: NewsResponse.Article) {

//        _setonclick.onNewsItemClicked(article)

        val intent = Intent(contex, NewsDetailActivity::class.java)
        intent.putExtra("USER", article)
        contex.startActivity(intent)

//        val args = Bundle()
//        args.putParcelable("USER", article)
//        Navigation.findNavController(view).navigate(R.id.newsdetails_fragment, args)

    }


    class Items(binding: BreakingNewsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var binding: BreakingNewsAdapterBinding = binding
    }
}

