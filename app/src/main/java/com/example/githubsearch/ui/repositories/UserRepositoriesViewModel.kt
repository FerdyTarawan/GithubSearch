package com.example.githubsearch.ui.repositories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.model.Repo
import com.example.githubsearch.model.entity.User
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _repoLoadingState: MutableState<NetworkState> =
        mutableStateOf(NetworkState.IDLE)
    val repoLoadingState: State<NetworkState> get() = _repoLoadingState

    val repo: State<MutableList<Repo>> = mutableStateOf(mutableListOf())
    val userDataFlow: MutableStateFlow<User?> = MutableStateFlow(null)
    val repoPageStateFlow: MutableStateFlow<Int> = MutableStateFlow(1)

    private val newRepoFlow =
        repoPageStateFlow.combine(userDataFlow.filterNotNull()) { page, userData ->
            Pair(
                page,
                userData
            )
        }.flatMapLatest { (page, userData) ->
            _repoLoadingState.value = NetworkState.LOADING
            githubRepository.getUserRepositories(
                params = "user:${userData.login}",
                page = page,
                onSuccess = { _repoLoadingState.value = NetworkState.SUCCESS },
                onError = { _repoLoadingState.value = NetworkState.ERROR })
        }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), replay = 1)

    fun getUserData(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getUser(username).collect { userDataFlow.value = it }
            newRepoFlow.collectLatest {
                repo.value.addAll(it)
            }
        }
    }

    fun loadNextRepoPage() {
        if (repoLoadingState.value != NetworkState.LOADING) {
            repoPageStateFlow.value++
        }
    }
}