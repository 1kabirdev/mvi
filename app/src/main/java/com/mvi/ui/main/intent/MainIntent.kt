package com.mvi.ui.main.intent

sealed class MainIntent {
    object FetchUser : MainIntent()

}