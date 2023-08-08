package com.di

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.CustomActionBarBinding
import com.utlis.CustomActionBar
import com.utlis.callbackinterface.OnBackButtonClicked
import dagger.Module
import dagger.Provides
import org.jetbrains.annotations.Nullable

@Module

class CustomActionBarModule{
    @Provides
    fun provideViewBinding(binding: CustomActionBarBinding): CustomActionBar {
        return CustomActionBar(binding)
    }

}