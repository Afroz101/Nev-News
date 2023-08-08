package com.screens.activity

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.myapplication.R
import com.example.myapplication.databinding.NewsdetailsFragmentBinding
import com.model.NewsResponse
import com.base.BaseActivity
import com.di.DaggerApplicationComponent
import com.utlis.CustomActionBar
import com.utlis.callbackinterface.OnBackButtonClicked
import javax.inject.Inject

class NewsDetailActivity : BaseActivity<NewsdetailsFragmentBinding>(R.layout.newsdetails_fragment) {
    @Inject
    lateinit var customActionBar: CustomActionBar
    private var article: NewsResponse.Article? = null
    override fun init() {
        super.init()
        builderDaggerComponent()
        article = intent.getParcelableExtra("USER")

//        article= requireArguments().getParcelable("USER")

        binding.newsUrl = article?.url
        webViewLoadListener()
        setActionbar()
    }


    private fun builderDaggerComponent() {
//        DaggerApplicationComponent.factory().create(this, binding.actionbarView)
////            .customActionBarModule(CustomActionBarModule(binding.actionbarView)).
////            activityModule(
////                ActivityModule(this))
//            .inject(this)

        DaggerApplicationComponent.factory().create(this, binding.actionbarView)
            .inject(this)
    }

    private fun webViewLoadListener() {
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.loadingBarStatus = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.loadingBarStatus = View.INVISIBLE
            }
        }
    }

    private fun setActionbar() {
        customActionBar.setActionBar(article?.source?.name.toString())
        customActionBar.showBackButton()
        customActionBar.setActionbarBackPress(object : OnBackButtonClicked {
            override fun backButtonClicked() {
                onBackPressed()
            }
        });
    }


}