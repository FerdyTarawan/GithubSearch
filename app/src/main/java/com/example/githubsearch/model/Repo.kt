package com.example.githubsearch.model

data class Repo(
    val id: Long,
    val name: String,
    val ownerAvatarUrl: String?,
    val description: String?,
    val stargazersCount: Number,
    val updatedAt: String,
)
