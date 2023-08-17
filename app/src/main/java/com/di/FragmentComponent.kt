package com.di

import com.example.myapplication.databinding.CustomActionBarBinding
import com.screens.fragment.NewsDetailActivity
import com.screens.fragment.BreakingNewsFragment
import com.screens.fragment.BusinessFragment
import com.screens.fragment.CricketFragment
import com.screens.fragment.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Subcomponent

//@ActivityScope
//@Component(dependencies = [ApplicationComponent::class], modules = [CustomActionBarModule::class,interfaceModule::class])
//interface FragmentComponent {
//
//    fun inject(breakingNewsFragment: BreakingNewsFragment)
//    fun inject(businessFragment: BusinessFragment)
//    fun inject(newsDetailFragment: NewsDetailActivity)
//
//    fun getNewsClickCallback():setonItemClick
//
//    @Component.Factory
//    interface Factory {
//        fun create(
//            @BindsInstance context: Context,
//            @BindsInstance binding: CustomActionBarBinding,
//            applicationComponent: ApplicationComponent
//        ): FragmentComponent
//    }
//}


@ActivityScope
@Subcomponent(modules = [CustomActionBarModule::class, interfaceModule::class])
interface FragmentComponent {

    fun inject(breakingNewsFragment: BreakingNewsFragment)
    fun inject(businessFragment: BusinessFragment)
    fun inject(newsDetailFragment: NewsDetailActivity)
    fun inject(cricketFragment: CricketFragment)
    fun inject(favoriteFragment: FavoriteFragment)


    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance binding: CustomActionBarBinding
        ): FragmentComponent

    }

}