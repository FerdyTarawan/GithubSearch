package com.example.githubsearch.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import com.example.githubsearch.ui.composables.DUMMY_USER
import com.example.githubsearch.ui.composables.Loading
import com.example.githubsearch.ui.composables.ProfileItem
import com.example.githubsearch.ui.theme.GithubSearchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSearchTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    ProfileItem(DUMMY_USER)
                }
            }
        }
    }
}