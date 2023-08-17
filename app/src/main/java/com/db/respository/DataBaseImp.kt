package com.db.respository

import com.db.FavoriteNewsDB
import com.model.NewsResponse
import com.repository.DataBaseRepository
import javax.inject.Inject

class DataBaseImp @Inject constructor(private val favoriteNewsDB: FavoriteNewsDB) :
    DataBaseRepository {

    override suspend fun saveFavorite(news: NewsResponse.Article) {
        favoriteNewsDB.getFavoriteDAO().addNewsToFav(news)
    }

    override suspend fun removeNewsFromFavorite(news: NewsResponse.Article) {
        favoriteNewsDB.getFavoriteDAO().deleteFavNews(news.url!!)
    }

    override suspend fun getAllFavNewsList(): List<NewsResponse.Article> {
        return favoriteNewsDB.getFavoriteDAO().getAllFavNewsList()
    }

    //
    override suspend fun newsAlreadyAdded(news: String): Boolean {

        return favoriteNewsDB.getFavoriteDAO().isAlreadyAdded(news) != null

    }


}