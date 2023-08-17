package com.di

import android.content.Context
import androidx.room.Room
import com.db.FavoriteNewsDB
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun roomInstance(context: Context): FavoriteNewsDB {
        return Room.databaseBuilder(context, FavoriteNewsDB::class.java, "FavoriteNewsDB").build()
    }

}