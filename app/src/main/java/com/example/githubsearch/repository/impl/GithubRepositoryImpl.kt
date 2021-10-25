package com.example.githubsearch.repository.impl

import com.example.githubsearch.model.User
import com.example.githubsearch.model.dto.toDomain
import com.example.githubsearch.network.GithubService
import com.example.githubsearch.repository.GithubRepository
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GithubRepositoryImpl(
    private val githubService: GithubService,
    private val defaultDispatcher: CoroutineDispatcher
) : GithubRepository {
    override suspend fun searchUsers(
        params: String,
        page: Int?,
        sort: String?,
        order: String?,
        perPage: Int?,
        onSuccess: (() -> Unit)?,
        onError: (() -> Unit)?
    ): Flow<List<User>> = flow {
        val response = githubService.searchUsers(params, page, sort, order, perPage)
        response.suspendOnSuccess {
            val usersDto = data.items
            val users = mutableListOf<User>()

            usersDto.asFlow().map { userDto ->
                getUser(userDto.login).collect { users.add(it) }
            }.collect()

            emit(users.toList())
            onSuccess?.invoke()
        }.onError {
            onError?.invoke()
        }
    }.onCompletion { onSuccess?.invoke() }.flowOn(Dispatchers.IO)

    override suspend fun getUser(
        username: String,
        onSuccess: (() -> Unit)?,
        onError: (() -> Unit)?
    ): Flow<User> = flow {
        val response = githubService.getUser(username)

        response.suspendOnSuccess {
            val user = data.toDomain()
            emit(user)
            onSuccess?.invoke()
        }.onError {
            onError?.invoke()
        }
    }.onCompletion { onSuccess?.invoke() }.flowOn(Dispatchers.IO)
}