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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.githubsearch.model.entity.User
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.placeholder.material.placeholder

val DUMMY_USER: User = User(
    id=123,
    login = "johndoe",
    name = "John Doe",
    avatarURL = "https://avatars.githubusercontent.com/u/37?v=4",
    bio = "Test User",
    blog = "Testing Testing",
    company = "Test Company",
    email = "john@doe.com",
    followers = "100K Followers",
    following = "150 Following",
    location = "New York"
)

@Composable
fun ProfileItem(
    user: User,
    modifier: Modifier = Modifier,
    imgModifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
            .then(modifier)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter(data = user.avatarURL),
                    contentDescription = "User avatar",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .then(imgModifier),
                )
            }

            Spacer(Modifier.width(8.dp))

            Column {
                FlowRow(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = user.name,
                        modifier = Modifier.padding(end = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                    Text(
                        text = "@${user.login}",
                        fontSize = 10.sp,
                    )
                }

                user.bio?.let {
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = it,
                        maxLines = 3,
                        fontSize = 14.sp,
                    )
                    Spacer(Modifier.height(5.dp))
                }

                Spacer(Modifier.height(5.dp))

                FlowRow {
                    user.location?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(end = 8.dp),
                            fontSize = 10.sp,
                        )
                    }

                    user.email?.let {
                        Text(text = it, fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
    ProfileItem(user = DUMMY_USER, imgModifier = Modifier.placeholder(visible = true)) {}
}