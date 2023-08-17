package com.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<Binding : ViewDataBinding>( @LayoutRes private val layoutId: Int) : AppCompatActivity() {
    protected lateinit var binding: Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        init()
        observeViewModel()
    }

    open fun init() {}
    open fun observeViewModel(){}
    fun showSnackbarMessage(message:String) {
        Snackbar.make(binding.root, message, 5000).show()

    }


}