package com.mvi.data.service.api.impl

import com.mvi.data.models.User
import com.mvi.data.service.api.ApiService

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }

    override suspend fun getUser(login: String): User {
        return apiService.getUser(login)
    }
}