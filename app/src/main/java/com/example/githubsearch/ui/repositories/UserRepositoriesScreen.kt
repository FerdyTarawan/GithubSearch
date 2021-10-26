package com.example.githubsearch.ui.repositories

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.network.onLoading
import com.example.githubsearch.ui.composables.*
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

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)) {
        ProfileDetail(user = userData.value)
        ListDivider()
        LazyColumn(state = rememberLazyListState()) {
            paging(
                items = repo,
                currentIndexFlow = vm.repoPageStateFlow,
                fetch = { vm.loadNextRepoPage() }
            ) {
                RepoItem(repo = it)
                ListDivider()
            }

            item {
                networkState.onLoading {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Loading(Modifier.padding(10.dp))
                    }
                }
            }
        }

        if (repo.isEmpty() && networkState == NetworkState.SUCCESS) {
            EmptyList(text = "No Repositories")
        }
    }
}