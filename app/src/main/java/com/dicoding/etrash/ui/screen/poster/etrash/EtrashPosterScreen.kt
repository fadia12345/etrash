package com.dicoding.etrash.ui.screen.poster.etrash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dicoding.etrash.R
import com.dicoding.etrash.navigation.Screen
import com.dicoding.etrash.ui.theme.AlegreyaSansFontFamily

class EtrashPosterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            EtrashPosterScreenContent(navController)
        }
    }
}

@Composable
fun EtrashPosterScreenContent(navController: NavController) {
    // Dummy data
    val posters = listOf(
        Poster(
            title = "Apa Itu E-Trash",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere, libero vel fermentum efficitur.",
            imageRes = R.drawable.backgroundhome
        )
    )

    LazyColumn {
        items(posters) { poster ->
            PosterItem(poster = poster, navController = navController)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PosterItem(poster: Poster, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(16.dp)
            .clickable {
                navController?.navigate(Screen.PosterEtrash.route)
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image
            Image(
                painter = painterResource(id = poster.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                contentScale = ContentScale.Crop
            )

            // Title
            Text(
                text = poster.title,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFFBEC2C2)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // Description
            Text(
                text = poster.description,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = AlegreyaSansFontFamily,
                    color = Color(0xFFBEC2C2)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}

data class Poster(
    val title: String,
    val description: String,
    val imageRes: Int
)

//@Preview(showBackground = true)
//@Composable
//fun PosterItemPreview() {
//    val dummyPoster = Poster(
//        title = "Etrash Poster 1",
//        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer posuere, libero vel fermentum efficitur.",
//        imageRes = R.drawable.backgroundhome
//    )
//    PosterItem(poster = dummyPoster, navContro)
//}


