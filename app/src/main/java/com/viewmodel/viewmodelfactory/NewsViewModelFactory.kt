package com.viewmodel.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.repository.NewsRepository
import com.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsViewModelFactory<T>(private val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel>create(modelClass: Class<T>): T {
        return creator() as T
    }
}