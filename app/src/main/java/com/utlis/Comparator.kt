package com.utlis

import androidx.recyclerview.widget.DiffUtil
import com.model.NewsResponse

class Comparator {
    companion object {
         val COMPARATOR = object : DiffUtil.ItemCallback<NewsResponse.Article>() {
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