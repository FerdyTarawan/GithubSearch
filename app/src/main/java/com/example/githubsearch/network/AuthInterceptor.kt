package com.example.githubsearch.network

import com.example.githubsearch.ui.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest
            .newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.ACCESS_TOKEN}")
            .url(originalRequest.url)
            .build()

        return chain.proceed(newRequest)
    }
}