package com.example.githubsearch.ui.users

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.model.entity.User
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _usersLoadingState: MutableState<NetworkState> = mutableStateOf(NetworkState.IDLE)
    val usersLoadingState: State<NetworkState> get() = _usersLoadingState

    val users: State<MutableList<User>> = mutableStateOf(mutableListOf())
    val usersPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)
    val searchQueryStateFlow: MutableStateFlow<String> = MutableStateFlow("")

    private val newUsersFlow =
        usersPageStateFlow.combine(searchQueryStateFlow) { page: Int, query: String ->
            Pair(
                page,
                query
            )
        }.debounce(300).flatMapLatest { (page, query) ->
            _usersLoadingState.value = NetworkState.LOADING
            githubRepository.searchUsers(
                params = if (query.isBlank()) "type:user" else "type:user $query in:name $query in:login",
                page = page,
                onSuccess = { _usersLoadingState.value = NetworkState.SUCCESS },
                onError = { _usersLoadingState.value = NetworkState.ERROR }
            )
        }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newUsersFlow.collectLatest {
                users.value.addAll(it)
            }
        }
    }

    fun loadNextUsersPage() {
        if (usersLoadingState.value != NetworkState.LOADING) {
            usersPageStateFlow.value++
        }
    }

    fun updateSearchQuery(text: String) {
        searchQueryStateFlow.value = text
        usersPageStateFlow.value = 1
        users.value.clear()
    }
}