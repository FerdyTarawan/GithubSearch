package com.example.githubsearch.ui.users

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.ui.composables.ListDivider
import com.example.githubsearch.ui.composables.Loading
import com.example.githubsearch.ui.composables.ProfileItem
import com.example.githubsearch.utils.paging
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun UsersScreen(vm: UsersViewModel = viewModel()) {
    val networkState by vm.usersLoadingState
    val users by vm.users

    LazyColumn(state = rememberLazyListState()) {
        paging(
            items = users,
            currentIndexFlow = vm.usersPageStateFlow,
            fetch = { vm.loadNextUsersPage() }) {
            ProfileItem(user = it)
            ListDivider()
        }
    }

    if (networkState == NetworkState.LOADING) {
        Loading()
    }
}