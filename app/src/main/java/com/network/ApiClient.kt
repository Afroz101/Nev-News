package com.network

import com.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        pageNumber: Int = 0,
        @Query("apiKey")
        apiKey: String = ApiConstant.API_KEY
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getBusinessNews(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        pageNumber: Int = 0,
        @Query("apiKey")
        apiKey: String = ApiConstant.API_KEY,
        @Query("category")
        category: String = "business"

    ): Response<NewsResponse>


}