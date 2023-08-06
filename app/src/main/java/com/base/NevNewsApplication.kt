package com.base
import android.app.Application
import com.di.ApplicationComponent
import com.di.DaggerApplicationComponent

class NevNewsApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}