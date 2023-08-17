package com.screens.activity

import android.view.MenuItem
import androidx.navigation.findNavController
import com.base.BaseActivity
import com.base.LifecycleObserverHandler
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBreakingNewsBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.utlis.hide
import com.utlis.show

//enum class ActiveFragment {
//    BREAKING_NEWS,
//    BUSINESS_NEWS,
//    CRICKET_NEWS,
//    FAV_SCREEN,
//}

class MainActivityActivity :
    BaseActivity<ActivityBreakingNewsBinding>(R.layout.activity_breaking_news),
    OnItemSelectedListener {
//    private var active: ActiveFragment = ActiveFragment.BREAKING_NEWS

    override fun init() {
        super.init()
        binding.bottomNavigationView.setOnItemSelectedListener(this)
        lifecycle.addObserver(LifecycleObserverHandler())
    }


    private fun hideBottomMenu() {
        binding.bottomNavigationView.hide()
    }

    private fun showBottomMenu() {
        binding.bottomNavigationView.show()
    }

    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
        showBottomMenu()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
//                active = ActiveFragment.BREAKING_NEWS
                findNavController(R.id.nag_host).navigate(R.id.breakking_news_fragment)
            }

            R.id.business -> {
//                active = ActiveFragment.BUSINESS_NEWS
                findNavController(R.id.nag_host).navigate(R.id.businessFragment)
            }

            R.id.cricket -> {
                hideBottomMenu()
//                active = ActiveFragment.CRICKET_NEWS
                findNavController(R.id.nag_host).navigate(R.id.cricketFragment)
            }

        }
        return true
    }

}