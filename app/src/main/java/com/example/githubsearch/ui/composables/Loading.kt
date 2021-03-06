package com.example.githubsearch.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HourglassTop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.size(50.dp), shape = CircleShape, elevation = 4.dp) {
        Icon(
            Icons.Outlined.HourglassTop,
            "hourglass",
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    Loading()
}