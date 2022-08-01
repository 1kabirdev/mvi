package com.mvi.data.api.impl

import com.mvi.data.User

interface ApiHelper {

    suspend fun getUsers(): List<User>
}