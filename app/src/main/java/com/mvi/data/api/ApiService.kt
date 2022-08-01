package com.mvi.data.api

import com.mvi.data.models.User
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>

    object RetrofitBuilder {
        private const val BASE_URL = " https://api.github.com/"

        private fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


        val apiService: ApiService = getRetrofit().create(ApiService::class.java)
    }
}