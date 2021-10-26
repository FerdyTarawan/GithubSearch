package com.example.githubsearch.network

import androidx.compose.runtime.Composable

enum class NetworkState {
    IDLE,
    LOADING,
    ERROR,
    SUCCESS
}

@Composable
fun NetworkState.onLoading(loadingStateUi: @Composable () -> Unit): NetworkState {
    if (this == NetworkState.LOADING) {
        loadingStateUi()
    }
    return this
}