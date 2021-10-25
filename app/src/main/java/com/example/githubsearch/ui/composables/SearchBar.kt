package com.example.githubsearch.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit) {
    TextField(value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview () {
    SearchBar(value = "Testing", onValueChange = {})
}