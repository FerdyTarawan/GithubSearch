package com.example.githubsearch.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto (
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

    @Json(name = "score")
    val score: Double
)