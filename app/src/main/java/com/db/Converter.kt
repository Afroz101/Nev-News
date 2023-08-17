package com.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.model.NewsResponse


class Converter {

    @TypeConverter
    fun fromString(value: String?): NewsResponse.Article.Source? {
        return Gson().fromJson(value, NewsResponse.Article.Source::class.java)
    }

    @TypeConverter
    fun fromSource(project: NewsResponse.Article.Source?): String? {
        return Gson().toJson(project)
    }

}