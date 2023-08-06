package com.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.repository.NewsRepository


class NewsViewModel(
    newsRepository: NewsRepository) : ViewModel() {

    val list = newsRepository.getBreakingNewsPag().cachedIn(viewModelScope)




}