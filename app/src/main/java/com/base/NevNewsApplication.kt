package com.base
import android.app.Application
import com.di.ApplicationComponent
import com.di.CustomActionBarModule
//import com.di.DaggerApplicationComponent
import com.example.myapplication.databinding.CustomActionBarBinding

class NevNewsApplication : Application() {
//    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
//        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}