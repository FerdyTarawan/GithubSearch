package com.example.githubsearch.repository

import com.example.githubsearch.model.User

interface GithubRepository {
    suspend fun searchUsers(
        params: String = "",
        page: Int? = null,
        sort: String? = null,
        order: String? = null,
        perPage: Int? = 20
    ): List<User>

    suspend fun getUser(username: String): User
}