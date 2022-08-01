package com.mvi.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvi.data.service.api.impl.ApiHelper
import com.mvi.ui.detailsUser.repository.DetailsRepository
import com.mvi.ui.detailsUser.viewmodel.DetailsViewModel
import com.mvi.ui.main.repository.MainRepository
import com.mvi.ui.main.viewmodel.MainViewModel

/**
 * Factory MainActivity user list github
 */
class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}

/**
 * Factory DetailsUserActivity user details data github
 */
class ViewModelDetailsFactory(private var apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(DetailsRepository(apiHelper)) as T
        throw IllegalArgumentException("Unknown class name")
    }
}