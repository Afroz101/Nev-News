package com.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.base.BaseViewModel
import com.repository.BusinessRepository
import com.repository.CricketRepository
import com.repository.NewsRepository

class CricketViewModel(
    businessRepository: CricketRepository) : BaseViewModel() {

    val list = businessRepository.getCricketNews().cachedIn(viewModelScope)


}