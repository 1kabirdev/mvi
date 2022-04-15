package com.mvi.ui.intent

sealed class MainIntent {
    object FetchUser : MainIntent()

}