package com.example.githubsearch.ui.repositories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubsearch.model.User
import com.example.githubsearch.ui.composables.Loading
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun UserRepositoriesScreen(username: String, vm: UserRepositoriesViewModel = viewModel()) {
    val userData by vm.userData

    LaunchedEffect(username) {
        vm.getUserData(username)
    }

    if (userData == null) {
        Loading()
    } else {
        Column(Modifier.fillMaxSize()) {
            Text(text = username)
            Text(text = userData?.name ?: "")
            Text(text = userData?.avatarURL ?: "")
            Text(text = userData?.followers ?: "")
        }
    }
}