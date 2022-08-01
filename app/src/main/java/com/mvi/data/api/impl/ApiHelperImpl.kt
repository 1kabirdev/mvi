package com.mvi.data.api.impl

import com.mvi.data.User
import com.mvi.data.api.ApiService

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}