package com.mvi.intent


/**
 * User details data github
 */
sealed class DetailsIntent {
    object User : DetailsIntent()
}

/**
 * User list github
 */
sealed class MainIntent {
    object FetchUser : MainIntent()
}