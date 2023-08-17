package com.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.db.respository.DataBaseImp
import com.model.NewsResponse
import com.network.ApiClient
import com.utlis.NetworkResult
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val dataBaseImp: DataBaseImp) {


    private val favNewsLiveData = MutableLiveData<NetworkResult<List<NewsResponse.Article>>>()
    val breakingNews: LiveData<NetworkResult<List<NewsResponse.Article>>>
        get() = favNewsLiveData

    suspend fun getFavoriteList() {

        favNewsLiveData.postValue(NetworkResult.Loading())
        val response = dataBaseImp.getAllFavNewsList()
        favNewsLiveData.postValue(NetworkResult.Success(response))

    }

}