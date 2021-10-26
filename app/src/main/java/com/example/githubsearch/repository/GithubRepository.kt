package com.example.githubsearch.repository

import com.example.githubsearch.model.Repo
import com.example.githubsearch.model.entity.User
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun searchUsers(
        params: String = "",
        page: Int? = null,
        sort: String? = null,
        order: String? = null,
        perPage: Int? = 30,
        onSuccess: (() -> Unit)? = null,
        onError: (() -> Unit)? = null,
    ): Flow<List<User>>

    suspend fun getUser(
        username: String,
        onSuccess: (() -> Unit)? = null,
        onError: (() -> Unit)? = null,
    ): Flow<User>

    suspend fun getUserRepositories(
        params: String,
        page: Int? = null,
        perPage: Int? = 30,
        onSuccess: (() -> Unit)? = null,
        onError: (() -> Unit)? = null,
    ): Flow<List<Repo>>
}