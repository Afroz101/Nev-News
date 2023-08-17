package com.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.base.BaseViewModel
import com.repository.BusinessRepository
import com.repository.NewsRepository

class BusinessViewModel(
    businessRepository: BusinessRepository
) : BaseViewModel() {

    val list = businessRepository.getBusinessNews().cachedIn(viewModelScope)


}