package com.di

import com.base.NevNewsApplication
import com.google.gson.GsonBuilder
import com.network.ApiClient
import com.network.ApiConstant
import com.screens.activity.NewsDetailActivity
import com.screens.fragment.BreakingNewsFragment
import com.utlis.callbackinterface.OnBackButtonClicked
import com.utlis.callbackinterface.setonItemClick
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun retrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    fun apiClientInstance(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)

    }


    @Provides
    fun getnewsItemClicked(): setonItemClick {
        return BreakingNewsFragment()

    }

}