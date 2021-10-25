package com.example.githubsearch.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubsearch.ui.theme.GithubSearchTheme
import com.example.githubsearch.ui.users.UsersScreen
import com.example.githubsearch.ui.users.UsersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun GithubSearchRoot() {
    val navController = rememberNavController()

    GithubSearchTheme {
        Scaffold { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "user_search",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("user_search") {
                    val viewModel = hiltViewModel<UsersViewModel>()
                    UsersScreen(navController, viewModel)
                }
            }
        }
    }
}