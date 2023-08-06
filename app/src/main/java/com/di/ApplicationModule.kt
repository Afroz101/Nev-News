package com.di

import com.base.NevNewsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NevNewsApplication) {

    @Provides
    @Singleton
    fun provideApplication(): NevNewsApplication {
        return application
    }
}