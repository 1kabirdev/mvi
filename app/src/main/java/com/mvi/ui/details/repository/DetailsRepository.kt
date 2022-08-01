package com.mvi.ui.details.repository

import com.mvi.data.api.impl.ApiHelper

class DetailsRepository(private var apiHelper: ApiHelper) {
    suspend fun getUser(login: String) = apiHelper.getUser(login)
}