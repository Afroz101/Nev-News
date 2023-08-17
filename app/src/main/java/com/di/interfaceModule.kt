package com.di

import com.screens.fragment.BreakingNewsFragment
import com.screens.fragment.BusinessFragment
import com.utlis.callbackinterface.setonItemClick
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class interfaceModule {
    @Provides
    fun getNewsItemClicked(): setonItemClick {
        return BreakingNewsFragment()
    }
//    @Provides
//    fun getNewsItemClickedBusinessFragment(): setonItemClick {
//        return BusinessFragment()
//    }


}