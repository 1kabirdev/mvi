package com.mvi.data.api

import com.mvi.data.User

interface ApiHelper {

    suspend fun getUsers(): List<User>
}