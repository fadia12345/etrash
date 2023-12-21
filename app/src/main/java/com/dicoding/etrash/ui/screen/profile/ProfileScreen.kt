package com.dicoding.etrash.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.etrash.R
import com.dicoding.etrash.model.bottombar.BottomBar
import com.dicoding.etrash.navigation.Screen
import com.dicoding.etrash.ui.components.button.Button
import com.dicoding.etrash.ui.theme.EtrashTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {

    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)  // Gunakan innerPadding di sini
        ) {
            item {
                // Avatar di tengah atas dengan crop lingkaran
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.stock),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(120.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                ProfileInfoItem(
                    icon = Icons.Default.Person,
                    label = "Username",
                    value = "Aditya"
                )
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                ProfileInfoItem(
                    icon = Icons.Default.Email,
                    label = "Email",
                    value = "aditya@example.com"
                )
            }


            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Button(
                    text = "Logout",
                    onClick = {
                        navController.navigate(Screen.Welcome.route)
                    })
            }
        }

    }

}

@Composable
fun ProfileInfoItem(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    EtrashTheme {
        ProfileScreen(rememberNavController())
    }
}

