package com.mvi.ui.main.repository

import com.mvi.data.service.api.impl.ApiHelper

class MainRepository(private var apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}