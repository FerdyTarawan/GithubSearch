package com.example.githubsearch.ui.users

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.example.githubsearch.ui.composables.EmptyList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.network.onLoading
import com.example.githubsearch.ui.composables.*
import com.example.githubsearch.utils.paging
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
fun UsersScreen(navController: NavController, vm: UsersViewModel = viewModel()) {
    val networkState by vm.usersLoadingState
    val users by vm.users
    val searchQuery = vm.searchQueryStateFlow.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        SearchBar(
            value = searchQuery.value,
            onValueChange = { newVal -> vm.updateSearchQuery(newVal) }
        )
        Spacer(Modifier.height(10.dp))
        Card(modifier = Modifier.fillMaxWidth(), elevation = 4.dp) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp)
            ) {
                LazyColumn(state = rememberLazyListState()) {
                    paging(
                        items = users,
                        currentIndexFlow = vm.usersPageStateFlow,
                        fetch = { vm.loadNextUsersPage() }
                    ) {
                        ProfileItem(user = it) {
                            navController.navigate("user_repository/${it.login}")
                        }
                        ListDivider()
                    }

                    item {
                        networkState.onLoading {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Loading(Modifier.padding(10.dp))
                            }
                        }
                    }
                }

                if (users.isEmpty() && networkState == NetworkState.SUCCESS){
                    EmptyList(text = "Not Found")
                }
            }
        }
    }
}