package com.mvi.data.service.api

import com.mvi.data.models.User
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{login}")
    suspend fun getUser(
        @Path("login") login: String
    ): User

    object RetrofitBuilder {
        private const val BASE_URL = " https://api.github.com/"

        private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


        val apiService: ApiService = getRetrofit().create(ApiService::class.java)
    }
}