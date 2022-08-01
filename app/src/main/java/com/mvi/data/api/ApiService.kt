package com.mvi.data.api

import com.mvi.data.models.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}