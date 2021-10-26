package com.example.githubsearch.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Place
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.githubsearch.model.User
import com.google.accompanist.placeholder.material.placeholder

@Composable
fun ProfileDetail(user: User?, modifier: Modifier = Modifier) {
    val showPlaceholder = user == null

    Box(modifier = modifier.padding(10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter(data = user?.avatarURL),
                    contentDescription = "User avatar",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .placeholder(visible = showPlaceholder),
                )
            }

            Spacer(Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .placeholder(visible = showPlaceholder)
            ) {
                Text(
                    text = user?.name ?: "",
                    modifier = Modifier.padding(end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(
                    text = "@${user?.login}",
                    fontSize = 10.sp,
                )

                user?.bio?.let {
                    Spacer(Modifier.height(5.dp))
                    Text(
                        text = it,
                        maxLines = 3,
                        fontSize = 14.sp,
                    )
                    Spacer(Modifier.height(5.dp))
                }

                Spacer(Modifier.height(5.dp))

                Row {
                    Icon(
                        Icons.Outlined.Groups,
                        contentDescription = "location",
                    )

                    Text(
                        text = user?.followers ?: "",
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 14.sp,
                    )

                    Text(
                        text = user?.following ?: "",
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 14.sp,
                    )
                }


                user?.location?.let {
                    Spacer(Modifier.height(5.dp))
                    Row {
                        Icon(Icons.Outlined.Place, contentDescription = "location")
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 10.dp),
                            fontSize = 14.sp,
                        )
                    }
                }

                user?.email?.let {
                    Spacer(Modifier.height(5.dp))
                    Row {
                        Icon(Icons.Outlined.Email, contentDescription = "email")
                        Text(
                            text = it,
                            modifier = Modifier.padding(start = 10.dp),
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileDetailPreview() {
    Column {
        ProfileDetail(user = DUMMY_USER)
        ListDivider()
        ProfileDetail(user = null)
    }
}