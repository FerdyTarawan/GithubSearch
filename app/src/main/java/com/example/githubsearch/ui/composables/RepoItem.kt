package com.example.githubsearch.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.githubsearch.model.Repo

val DUMMY_REPO: Repo = Repo(
    id=123,
    name = "Test",
    ownerAvatarUrl = "https://avatars.githubusercontent.com/u/37?v=4",
    updatedAt = "2 days ago",
    stargazersCount = 200,
    description = "Test Repo"
)

@Composable
fun RepoItem(repo: Repo, modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = rememberImagePainter(data = repo.ownerAvatarUrl),
                    contentDescription = "User avatar",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .then(modifier),
                )
            }

            Spacer(Modifier.width(8.dp))

            Column {
                Text(
                    text = repo.name,
                    modifier = Modifier.padding(end = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )

                repo.description?.let {
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
                    Icon(Icons.Outlined.StarBorder, contentDescription = "Star", modifier = Modifier.size(14.dp))
                    Text(
                        text = repo.stargazersCount.toString(),
                        modifier = Modifier.padding(end = 8.dp),
                        fontSize = 10.sp,
                    )

                    Text(text = repo.updatedAt, fontSize = 10.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview() {
    RepoItem(repo = DUMMY_REPO)
}