package com.example.githubsearch.model.dto

import com.example.githubsearch.model.User
import com.example.githubsearch.utils.formatNumber
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDetailDto (
    @Json(name = "login")
    val login: String,

    @Json(name = "id")
    val id: Long,

    @Json(name = "node_id")
    val nodeID: String,

    @Json(name = "avatar_url")
    val avatarURL: String,

    @Json(name = "gravatar_id")
    val gravatarID: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "html_url")
    val htmlURL: String,

    @Json(name = "followers_url")
    val followersURL: String,

    @Json(name = "following_url")
    val followingURL: String,

    @Json(name = "gists_url")
    val gistsURL: String,

    @Json(name = "starred_url")
    val starredURL: String,

    @Json(name = "subscriptions_url")
    val subscriptionsURL: String,

    @Json(name = "organizations_url")
    val organizationsURL: String,

    @Json(name = "repos_url")
    val reposURL: String,

    @Json(name = "events_url")
    val eventsURL: String,

    @Json(name = "received_events_url")
    val receivedEventsURL: String,

    @Json(name = "type")
    val type: String,

    @Json(name = "site_admin")
    val siteAdmin: Boolean,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "company")
    val company: String? = null,

    @Json(name = "blog")
    val blog: String,

    @Json(name = "location")
    val location: String? = null,

    @Json(name = "email")
    val email: String? = null,

    @Json(name = "hireable")
    val hireable: Boolean? = null,

    @Json(name = "bio")
    val bio: String? = null,

    @Json(name = "twitter_username")
    val twitterUsername: String? = null,

    @Json(name = "public_repos")
    val publicRepos: Long,

    @Json(name = "public_gists")
    val publicGists: Long,

    @Json(name = "followers")
    val followers: Long,

    @Json(name = "following")
    val following: Long,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "updated_at")
    val updatedAt: String
)

fun UserDetailDto.toDomain(): User {
    return User(
        login = login,
        avatarURL = avatarURL,
        name = name ?: login,
        location = location,
        following = "${formatNumber(following)} Following",
        followers = "${formatNumber(followers)} Followers",
        email = email,
        blog = blog,
        bio = bio,
        company = company,
    )
}