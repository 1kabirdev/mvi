package com.mvi.data.service.api.impl

import com.mvi.data.models.User

interface ApiHelper {

    suspend fun getUsers(): List<User>
    suspend fun getUser(login: String): User
}