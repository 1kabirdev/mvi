package com.mvi.state

import com.mvi.data.models.User

sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val user: List<User>) : MainState()
    data class Error(val error: String?) : MainState()
}

sealed class DetailsState{
    object Idle : DetailsState()
    object Loading : DetailsState()
    data class Users(val user: User) : DetailsState()
    data class Error(val error: String?) : DetailsState()
}