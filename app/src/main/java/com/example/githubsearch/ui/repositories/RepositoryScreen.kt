package com.example.githubsearch.ui.repositories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RepositoryScreen(username: String) {
    Column(Modifier.fillMaxSize()) {
        Text(text = username)
    }
}