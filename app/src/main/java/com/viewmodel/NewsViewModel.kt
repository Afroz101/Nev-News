package com.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.base.BaseViewModel
import com.repository.NewsRepository


class NewsViewModel (newsRepository: NewsRepository) : BaseViewModel() {

    val list = newsRepository.getBreakingNewsPag().cachedIn(viewModelScope)





}