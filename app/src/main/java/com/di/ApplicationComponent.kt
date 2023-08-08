package com.di

import android.content.Context
import com.example.myapplication.databinding.CustomActionBarBinding
import com.screens.fragment.BreakingNewsFragment
import com.screens.fragment.BusinessFragment
import com.screens.activity.NewsDetailActivity
import dagger.BindsInstance
import dagger.Component
import org.jetbrains.annotations.Nullable
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    fun inject(breakingNewsFragment: BreakingNewsFragment)
    fun inject(businessFragment: BusinessFragment)
    fun inject(newsDetailFragment: NewsDetailActivity)


    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            @BindsInstance binding: CustomActionBarBinding
        ):
                ApplicationComponent

    }

}


