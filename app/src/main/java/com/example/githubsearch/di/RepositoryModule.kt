package com.example.githubsearch.di

import com.example.githubsearch.db.dao.UserDao
import com.example.githubsearch.network.GithubService
import com.example.githubsearch.repository.GithubRepository
import com.example.githubsearch.repository.impl.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@FlowPreview
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(githubService: GithubService, userDao: UserDao): GithubRepository {
        return GithubRepositoryImpl(
            githubService = githubService,
            userDao = userDao,
            defaultDispatcher = Dispatchers.IO
        )
    }
}