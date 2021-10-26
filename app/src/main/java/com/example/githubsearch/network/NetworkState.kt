package com.example.githubsearch.network

import androidx.compose.runtime.Composable

enum class NetworkState {
    IDLE,
    LOADING,
    ERROR,
    SUCCESS
}

@Composable
fun NetworkState.onLoading(block: @Composable () -> Unit): NetworkState {
    if (this == NetworkState.LOADING) {
        block()
    }
    return this
}