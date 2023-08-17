package com.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.google.android.material.snackbar.Snackbar
import com.model.NewsResponse

abstract class BaseFragment<Binding : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {
    protected lateinit var binding: Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, layoutId, container, false
        )
        init()
        observeViewModel()
        return binding.root

    }

    open fun init() {}
    open fun observeViewModel() {}
    open fun builderDaggerComponent() {}

    fun showSnackbarMessage(message:String) {
        Snackbar.make(binding.root, message, 5000).show()

    }

    fun callNewsDetailFragment(article:NewsResponse.Article){
        val bundle = Bundle()
        bundle.putParcelable("USER", article)
        findNavController().navigate(R.id.newsDetailFragment,bundle)

    }


//    fun callFavouriteFragment(article:NewsResponse.Article){
//
//        findNavController().navigate(R.id.favoriteFragment,bundle)
//
//    }


}