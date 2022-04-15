package com.mvi.data.repository

import com.mvi.data.api.ApiHelper

class MainRepository(private var apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}