package com.example.githubsearch.repository

import com.example.githubsearch.model.User
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun searchUsers(
        params: String = "",
        page: Int? = null,
        sort: String? = null,
        order: String? = null,
        perPage: Int? = 20,
        onSuccess: (() -> Unit)? = null,
        onError: (() -> Unit)? = null,
    ): Flow<List<User>>

    suspend fun getUser(
        username: String,
        onSuccess: (() -> Unit)? = null,
        onError: (() -> Unit)? = null,
    ): Flow<User>
}