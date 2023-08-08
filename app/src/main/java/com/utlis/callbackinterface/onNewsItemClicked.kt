package com.utlis.callbackinterface

import com.model.NewsResponse

interface setonItemClick {
    fun onNewsItemClicked(article: NewsResponse.Article)

}