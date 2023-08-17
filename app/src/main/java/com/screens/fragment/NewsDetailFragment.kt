package com.screens.fragment

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.databinding.NewsdetailsFragmentBinding
import com.model.NewsResponse
import com.base.BaseFragment
import com.base.NevNewsApplication
import com.db.respository.DataBaseImp
import com.screens.activity.MainActivityActivity
//import com.di.DaggerFragmentComponent
import com.utlis.CustomActionBar
import com.utlis.callbackinterface.OnBackButtonClicked
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailActivity : BaseFragment<NewsdetailsFragmentBinding>(R.layout.newsdetails_fragment) {
    @Inject
    lateinit var customActionBar: CustomActionBar

    @Inject
    lateinit var dataBaseImp: DataBaseImp

    private var article: NewsResponse.Article? = null
    private var isFav: Boolean = false

    override fun init() {
        super.init()
        builderDaggerComponent()
        article = arguments?.getParcelable("USER")

        binding.newsUrl = article?.url
        webViewLoadListener()
        setActionbar()
    }


    override fun builderDaggerComponent() {
        val component = (activity?.application as NevNewsApplication).applicationComponent
        component.getFragmentComponent().create(binding.actionbarView).inject(this)

//        DaggerFragmentComponent.factory().create(this, binding.actionbarView,component)
//            .inject(this)
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
        customActionBar.apply {
            setActionBar(article?.source?.name.toString())
            showBackButton()
            showFavIcon()

            setActionbarBackPress(object : OnBackButtonClicked {
                override fun backButtonClicked() {
                    (activity as MainActivityActivity).onBackPressed()
                }

                override fun otherActionbarIconClick(view: View) {
                    if (view.id == R.id.favouriteIcon) {
                        favIconClickHandler()
                    }


                }
            })
        }


        setFavoriteIcon()
    }

    private fun setFavoriteIcon() {
        lifecycleScope.launch(Dispatchers.Main) {
            article?.url.let {
                isFav = dataBaseImp.newsAlreadyAdded(it!!)
                if (!isFav) {
                    customActionBar.setFavIcon(R.drawable.favourite)
                } else {
                    customActionBar.setFavIcon(R.drawable.favourite_2)
                }

            }


        }
    }

    private fun favIconClickHandler() {
        lifecycleScope.launch(Dispatchers.IO) {
            if (!isFav) {
                dataBaseImp.saveFavorite(article!!)
                setFavoriteIcon()
            } else {
                dataBaseImp.removeNewsFromFavorite(article!!)
                setFavoriteIcon()
            }
        }
    }
}