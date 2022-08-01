package com.mvi.ui.details.viewstate

import com.mvi.data.models.User

sealed class DetailsViewState{
    object Idle : DetailsViewState()
    object Loading : DetailsViewState()
    data class Users(val user: User) : DetailsViewState()
    data class Error(val error: String?) : DetailsViewState()
}