package com.example.githubsearch.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "avatar_url") val avatarURL: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "blog") val blog: String,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "bio") val bio: String?,
    @ColumnInfo(name = "followers") val followers: String,
    @ColumnInfo(name = "following") val following: String,
)