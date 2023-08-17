package com.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.base.BaseViewModel
import com.model.NewsResponse
import com.repository.FavoriteRepository
import com.utlis.NetworkResult
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : BaseViewModel() {

    val breakingNews: LiveData<NetworkResult<List<NewsResponse.Article>>>
        get() = favoriteRepository.breakingNews

    init {
        viewModelScope.launch {
            favoriteRepository.getFavoriteList()
        }

    }

}