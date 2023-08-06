package com.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.repository.BusinessRepository
import com.repository.NewsRepository

class BusinessViewModel(
    businessRepository: BusinessRepository
) : ViewModel() {

    val list = businessRepository.getBusinessNews().cachedIn(viewModelScope)


}