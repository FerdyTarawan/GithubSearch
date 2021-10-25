package com.example.githubsearch.ui.repositories

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.ui.composables.ListDivider
import com.example.githubsearch.ui.composables.Loading
import com.example.githubsearch.utils.paging
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun UserRepositoriesScreen(username: String, vm: UserRepositoriesViewModel = viewModel()) {
    val userData = vm.userDataFlow.collectAsState()
    val repo by vm.repo
    val networkState by vm.repoLoadingState

    LaunchedEffect(username) {
        vm.getUserData(username)
    }

    if (userData.value == null) {
        Loading()
    } else {
        Column(Modifier.fillMaxSize()) {
            Text(text = username)
            Text(text = userData.value?.name ?: "")
            Text(text = userData.value?.avatarURL ?: "")
            Text(text = userData.value?.followers ?: "")

            LazyColumn(state = rememberLazyListState()) {
                paging(
                    items = repo,
                    currentIndexFlow = vm.repoPageStateFlow,
                    fetch = { vm.loadNextRepoPage() }
                ) {
                    Column {
                        Text(text = it.name)
                        it.description?.let { it1 -> Text(text = it1) }
                        Text(text = it.stargazersCount.toString())
                        Text(text = it.updatedAt)
                    }
                    ListDivider()
                }
            }

            if (networkState == NetworkState.LOADING) {
                Loading()
            }
        }
     }
}