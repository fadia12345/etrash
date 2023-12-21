package com.dicoding.etrash.ui.screen.recycle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.etrash.navigation.Screen
import com.dicoding.etrash.ui.theme.EtrashTheme

@Composable
fun RecycleScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Recycle Screen")

            // Tombol untuk mengarahkan ke Home Screen
            Button(onClick = { navController.navigate(Screen.Home.route) }) {
                Text("Go to Home")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecycleScreenPreview() {
    EtrashTheme {
        RecycleScreen(rememberNavController())
    }
}