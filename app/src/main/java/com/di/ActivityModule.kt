package com.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {
    @Provides
    fun getActivity(contex: Context): Context {
        return contex
    }

}