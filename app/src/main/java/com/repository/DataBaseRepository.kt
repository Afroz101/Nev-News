package com.repository

import com.model.NewsResponse

interface DataBaseRepository {

    suspend fun saveFavorite(news: NewsResponse.Article)
    suspend fun removeNewsFromFavorite(news: NewsResponse.Article)

    suspend fun getAllFavNewsList(): List<NewsResponse.Article>

    suspend fun newsAlreadyAdded(news: String): Boolean


}