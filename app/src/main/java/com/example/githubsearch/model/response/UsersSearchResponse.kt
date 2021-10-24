package com.example.githubsearch.model.response

import com.example.githubsearch.model.dto.UserDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UsersSearchResponse (
    @Json(name = "total_count")
    val totalCount: Long,

    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,

    @Json(name = "items")
    val items: List<UserDto>
)