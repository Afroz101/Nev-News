package com.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.network.ApiClient
import com.network.ApiConstant
import com.paging.NewsPagingSource
import com.paging.PagingSourceType
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiClient: ApiClient) {

//    private val breakingNewsLiveData = MutableLiveData<NetworkResult<NewsResponse>>()
//    val breakingNews: LiveData<NetworkResult<NewsResponse>>
//    get() = breakingNewsLiveData

//    suspend fun getBreakingNews() {
//        breakingNewsLiveData.postValue(NetworkResult.Loading())
//        val response = apiClient.getBreakingNews()
//        if (response.isSuccessful && response.body() != null) {
//            breakingNewsLiveData.postValue(NetworkResult.Success(response.body()!!))
//            println(response.body())
//        } else if (response.errorBody() != null) {
//            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
//            breakingNewsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//        } else {
//            breakingNewsLiveData.postValue(NetworkResult.Error("Error"))
//
//        }
//    }


    fun getBreakingNewsPag() = Pager(
        config = PagingConfig(ApiConstant.LIMIT.toInt(), maxSize = 100),
        pagingSourceFactory = {
            NewsPagingSource(apiClient, PagingSourceType.BREAKING_NEWS)

        }
    ).liveData


}