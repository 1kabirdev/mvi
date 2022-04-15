package com.mvi.data

import com.squareup.moshi.Json

data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "login")
    val login: String = "",
    @Json(name = "avatar_url")
    val avatar_url: String = ""
)