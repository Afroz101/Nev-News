package com.di

import com.screens.activity.BreakingNewsActivity
import com.screens.fragment.BreakingNewsFragment
import com.screens.fragment.BusinessFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,ApplicationModule::class])
interface ApplicationComponent {

    fun inject(breakingNewsFragment: BreakingNewsFragment)

    fun inject(businessFragment: BusinessFragment)


}