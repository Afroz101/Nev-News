package com.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.network.ApiClient
import com.network.ApiConstant
import com.paging.NewsPagingSource
import com.paging.PagingSourceType
import javax.inject.Inject

class CricketRepository @Inject constructor(private val apiClient: ApiClient) {

    fun getCricketNews() =
        Pager(
            config = PagingConfig(ApiConstant.LIMIT.toInt(), maxSize = 100),
            pagingSourceFactory = {
                NewsPagingSource(apiClient, PagingSourceType.CRICKET)
            }
        ).liveData


}