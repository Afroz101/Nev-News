package com.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
) {

    @Entity
    @Parcelize
    data class Article(
        @PrimaryKey(autoGenerate = true)
        var newsid: Int?,
        val author: String? = "",
        val content: String? = "",
        val description: String? = "",
        val publishedAt: String? = "",
        val source: Source? = Source("", ""),
        val title: String? = "",
        val url: String? = "",
        val urlToImage: String? = ""
    ) : Parcelable {

        @Parcelize
        data class Source(
            val id: String? = "",
            val name: String? = ""
        ) : Parcelable {

        }
    }
}





