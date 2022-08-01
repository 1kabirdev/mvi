package com.mvi.ui.main.viewstate

import com.mvi.data.models.User

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()
}