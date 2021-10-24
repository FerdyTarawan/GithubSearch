package com.example.githubsearch.repository.impl

import com.example.githubsearch.model.User
import com.example.githubsearch.network.GithubService
import com.example.githubsearch.repository.GithubRepository

class GithubRepositoryImpl(private val githubService: GithubService): GithubRepository {
    override suspend fun searchUsers(
        params: String,
        page: Int?,
        sort: String?,
        order: String?,
        perPage: Int?
    ): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(username: String): User {
        TODO("Not yet implemented")
    }
}