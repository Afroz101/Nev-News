package com.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.model.NewsResponse

@Database(entities = [NewsResponse.Article::class], version = 1)
@TypeConverters(Converter::class)
abstract class FavoriteNewsDB : RoomDatabase() {

    abstract fun getFavoriteDAO(): FavoriteDAO


}