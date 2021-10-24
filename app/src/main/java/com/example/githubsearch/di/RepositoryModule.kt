package com.example.githubsearch.di

import com.example.githubsearch.network.GithubService
import com.example.githubsearch.repository.GithubRepository
import com.example.githubsearch.repository.impl.GithubRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGithubRepository(githubService: GithubService): GithubRepository {
        return GithubRepositoryImpl(githubService = githubService)
    }
}