package com.example.githubsearch.ui.users

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.ui.composables.ListDivider
import com.example.githubsearch.ui.composables.Loading
import com.example.githubsearch.ui.composables.ProfileItem
import com.example.githubsearch.ui.composables.SearchBar
import com.example.githubsearch.utils.paging
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun UsersScreen(navController: NavController, vm: UsersViewModel = viewModel()) {
    val networkState by vm.usersLoadingState
    val users by vm.users
    val searchQuery = vm.searchQueryStateFlow.collectAsState()

    Column(Modifier.fillMaxSize()) {
        SearchBar(
            value = searchQuery.value,
            onValueChange = { newVal -> vm.updateSearchQuery(newVal) })
        Card(Modifier.padding(15.dp)) {
            LazyColumn(state = rememberLazyListState()) {
                paging(
                    items = users,
                    currentIndexFlow = vm.usersPageStateFlow,
                    fetch = { vm.loadNextUsersPage() }
                ) {
                    ProfileItem(
                        user = it,
                    ) { navController.navigate("user_repository/${it.login}") }
                    ListDivider()
                }
            }

            if (networkState == NetworkState.LOADING) {
                Loading()
            }
        }
    }
}