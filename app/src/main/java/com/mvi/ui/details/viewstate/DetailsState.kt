package com.mvi.ui.details.viewstate

import com.mvi.data.models.User

sealed class DetailsState{
    object Idle : DetailsState()
    object Loading : DetailsState()
    data class Users(val user: User) : DetailsState()
    data class Error(val error: String?) : DetailsState()
}