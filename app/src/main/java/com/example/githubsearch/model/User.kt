package com.example.githubsearch.model


data class User(
    val login: String,
    val avatarURL: String,
    val name: String,
    val company: String,
    val blog: String,
    val location: String,
    val email: String?,
    val bio: String?,
    val followers: String,
    val following: String,
)