package com.example.githubsearch.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.githubsearch.ui.theme.Gray200

@Composable
fun ListDivider(
    modifier: Modifier = Modifier,
    color: Color = Gray200,
    thickness: Dp = 2.dp,
    startIndent: Dp = 0.dp
) {
    Divider(
        modifier = modifier,
        color = color,
        thickness = thickness,
        startIndent = startIndent
    )
}

@Preview(showBackground = true)
@Composable
fun ListDividerPreview() {
    Surface(modifier = Modifier.background(Color.White).padding(10.dp)) {
        ListDivider()
    }
}