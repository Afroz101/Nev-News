package com.utlis.callbackinterface

import android.content.Context
import com.model.NewsResponse

interface setonItemClick {
    fun onNewsItemClicked(article: NewsResponse.Article,context: Context)

}