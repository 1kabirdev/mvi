package com.mvi.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvi.data.api.impl.ApiHelper
import com.mvi.ui.detailsUser.repository.DetailsRepository
import com.mvi.ui.detailsUser.viewmodel.DetailsViewModel
import com.mvi.ui.main.repository.MainRepository
import com.mvi.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

class ViewModelDetailsFactory(private var apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(DetailsRepository(apiHelper)) as T
        throw IllegalArgumentException("Unknown class name")
    }
}