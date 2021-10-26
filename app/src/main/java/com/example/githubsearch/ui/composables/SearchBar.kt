package com.example.githubsearch.ui.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(value: String, onValueChange: (String) -> Unit, colors: TextFieldColors? = null) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colors.background, shape = RoundedCornerShape(10))
            .clip(RoundedCornerShape(20)),
        leadingIcon = {
            Icon(Icons.Filled.Search, "Search")
        },
        colors = colors ?: TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.onSecondary,
            disabledTextColor = MaterialTheme.colors.onSecondary,
            backgroundColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    Box(modifier = Modifier.padding(10.dp)) {
        SearchBar(value = "Text", onValueChange = {})
    }
}