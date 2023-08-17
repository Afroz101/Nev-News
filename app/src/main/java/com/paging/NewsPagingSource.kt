package com.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.model.NewsResponse
import com.network.ApiClient
import com.network.ApiConstant
import org.json.JSONObject
import retrofit2.Response

enum class PagingSourceType {
    BREAKING_NEWS,
    BUSINESS,
    CRICKET,

}

class NewsPagingSource (

    private val apiClient: ApiClient,private val pagingSourceType: PagingSourceType): PagingSource<Int, NewsResponse.Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsResponse.Article> {

        return try {

            val position = params.key ?: 1


//            val response = apiClient.getBreakingNews(pageNumber = position)
            val response = apiResponse(pagingSourceType,apiClient,position)

            if (response.isSuccessful && response.body() != null) {

                LoadResult.Page(
                    data = response.body()!!.articles,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == getTotalNumberOfPage(response.body()!!.totalResults)) null else position + 1
                )

            } else {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                val errorMessage = errorObj.getString("message")
                LoadResult.Error(Throwable("API Error: ${response.code()} $errorMessage"))
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }


    override fun getRefreshKey(state: PagingState<Int, NewsResponse.Article>): Int? {
        if (state.anchorPosition != null) {
            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
            if (anchorPage?.prevKey != null) {
                return anchorPage.prevKey!!.plus(1)
            } else if (anchorPage?.nextKey != null) {
                return anchorPage.nextKey!!.minus(1)
            }
        } else {
            return null
        }
        return null
    }


    fun getTotalNumberOfPage(totalItems: Int): Int {
        val i: Int
        if (totalItems <= ApiConstant.LIMIT.toInt()) {
            return 0
        } else if (totalItems % ApiConstant.LIMIT.toInt() == 0) {
            i = totalItems / ApiConstant.LIMIT.toInt()
        } else {
            i = totalItems / ApiConstant.LIMIT.toInt() + 1
        }
        return i
    }

    private suspend fun apiResponse(pagingSourceType: PagingSourceType, apiClient: ApiClient, position:Int) : Response<NewsResponse> {

       return when(pagingSourceType) {
            PagingSourceType.BREAKING_NEWS -> apiClient.getBreakingNews(pageNumber = position)
            PagingSourceType.BUSINESS -> apiClient.getBusinessNews(pageNumber = position)
            PagingSourceType.CRICKET -> apiClient.getCricket(pageNumber = position)
        }

    }
}





