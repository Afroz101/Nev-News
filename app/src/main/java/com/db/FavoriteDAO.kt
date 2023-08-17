package com.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.model.NewsResponse

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewsToFav(article: NewsResponse.Article)


    @Query("Select * From Article where url =:url")
    suspend fun isAlreadyAdded(url: String): NewsResponse.Article

    @Query("Select * From Article")
    suspend fun getAllFavNewsList(): List<NewsResponse.Article>


    @Query("delete from Article WHERE url = :url")
    fun deleteFavNews(url: String)
}