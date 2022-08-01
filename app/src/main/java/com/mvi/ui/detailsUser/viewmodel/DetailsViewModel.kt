package com.mvi.ui.detailsUser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvi.intent.DetailsIntent
import com.mvi.state.DetailsState
import com.mvi.ui.detailsUser.repository.DetailsRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DetailsViewModel constructor(private var repository: DetailsRepository) : ViewModel() {
    val userIntent = Channel<DetailsIntent>(Channel.UNLIMITED)
    private var _state = MutableStateFlow<DetailsState>(DetailsState.Idle)
    val state: StateFlow<DetailsState> get() = _state

    fun handlerIntentUser(login: String) {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is DetailsIntent.User -> loadUser(login)
                }
            }
        }
    }

    private fun loadUser(login: String) {
        viewModelScope.launch {
            _state.value = DetailsState.Loading
            _state.value = try {
                DetailsState.Users(repository.getUser(login))
            } catch (e: java.lang.Exception) {
                DetailsState.Error(e.localizedMessage!!)
            }
        }
    }
}