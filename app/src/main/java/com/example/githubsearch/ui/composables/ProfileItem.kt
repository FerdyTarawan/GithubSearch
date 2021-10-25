package com.example.githubsearch.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.githubsearch.model.User

val DUMMY_USER: User = User(
    login = "jamesgolick",
    name = "James Golick",
    avatarURL = "https://avatars.githubusercontent.com/u/37?v=4",
    bio = "Test User",
    blog = "",
    company = "Normal",
    email = "wangshi@mihoyo.com",
    followers = "0",
    following = "0",
    location = "New York"
)

@Composable
fun ProfileItem(user: User, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = rememberImagePainter(data = user.avatarURL),
                contentDescription = "User avatar",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
            )
        }

        Spacer(Modifier.width(8.dp))

        Column {
            Row {
                Text(text = user.name)
                Spacer(Modifier.width(8.dp))
                Text(text = "@${user.login}")
            }

            Row {
                user.bio?.let { Text(text = it) }
            }

            Row {
                user.location?.let { Text(text = it) }
                Spacer(Modifier.width(16.dp))
                user.email?.let { Text(text = it) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
    ProfileItem(user = DUMMY_USER) {}
}