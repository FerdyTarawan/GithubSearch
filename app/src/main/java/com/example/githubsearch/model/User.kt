package com.example.githubsearch.model

data class User(
    val login: String,
    val avatarURL: String,
    val name: String,
    val company: String? = null,
    val blog: String,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    val followers: String,
    val following: String,
)