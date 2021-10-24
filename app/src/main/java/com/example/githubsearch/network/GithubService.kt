package com.example.githubsearch.network

import com.example.githubsearch.model.dto.UserDetailDto
import com.example.githubsearch.model.response.UsersSearchResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") params: String = "",
        @Query("page") page: Int? = null,
        @Query("sort") sort: String? = null,
        @Query("order") order: String? = null,
        @Query("per_page") perPage: Int? = 20,
    ): ApiResponse<UsersSearchResponse>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): ApiResponse<UserDetailDto>
}