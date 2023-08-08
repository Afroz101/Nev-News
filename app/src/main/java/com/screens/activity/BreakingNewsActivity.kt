package com.screens.activity

import android.content.Intent

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.base.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityBreakingNewsBinding
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import com.google.android.material.navigation.NavigationView

class BreakingNewsActivity :
    BaseActivity<ActivityBreakingNewsBinding>(R.layout.activity_breaking_news),
    OnItemSelectedListener {

    override fun init() {
        super.init()
        binding.bottomNavigationView.setOnItemSelectedListener(this)

    }
//
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home ->
                findNavController(R.id.nag_host).navigate(R.id.breakking_news_fragment)
            R.id.business ->
                findNavController(R.id.nag_host).navigate(R.id.businessFragment)
        }
        return true

    }


}