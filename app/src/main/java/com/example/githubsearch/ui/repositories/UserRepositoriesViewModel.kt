package com.example.githubsearch.ui.repositories

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearch.model.User
import com.example.githubsearch.network.NetworkState
import com.example.githubsearch.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _userRepoLoadingState: MutableState<NetworkState> =
        mutableStateOf(NetworkState.IDLE)
    val userRepoLoadingState: State<NetworkState> get() = _userRepoLoadingState
    val userData = mutableStateOf<User?>(null)

     fun getUserData(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            githubRepository.getUser(username).collect { userData.value = it }
        }
    }
}