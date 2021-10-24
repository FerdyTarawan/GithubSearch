package com.example.githubsearch.repository.impl

import com.example.githubsearch.model.User
import com.example.githubsearch.model.dto.toDomain
import com.example.githubsearch.network.GithubService
import com.example.githubsearch.repository.GithubRepository
import com.skydoves.sandwich.getOrThrow
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.withContext

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
    ) = flow<List<User>> {
        val response = githubService.searchUsers(params, page, sort, order, perPage)
        response.suspendOnSuccess {
            val usersDto = data.items
            val users = usersDto.map { getUser(it.login) }

            emit(users)
            onSuccess?.invoke()
        }.onError {
            onError?.invoke()
        }
    }.onCompletion { onSuccess?.invoke() }.flowOn(Dispatchers.IO)

    override suspend fun getUser(username: String): User = withContext(defaultDispatcher) {
        lateinit var data: User
        try {
            data = githubService.getUser(username).getOrThrow().toDomain()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return@withContext data
    }
}