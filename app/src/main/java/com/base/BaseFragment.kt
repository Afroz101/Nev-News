package com.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

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

    fun showSnackbarMessage(message:String) {
        Snackbar.make(binding.root, message, 5000).show()

    }


}