package com.dicoding.etrash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.etrash.navigation.Screen
import com.dicoding.etrash.ui.screen.home.HomeScreen
import com.dicoding.etrash.ui.screen.location.LocationScreen
import com.dicoding.etrash.ui.screen.login.LoginScreen
import com.dicoding.etrash.ui.screen.login.LoginViewModel
import com.dicoding.etrash.ui.screen.poster.etrash.EtrashPosterScreen
import com.dicoding.etrash.ui.screen.poster.etrash.EtrashPosterScreenContent
import com.dicoding.etrash.ui.screen.poster.etrash.PosterItem
import com.dicoding.etrash.ui.screen.profile.ProfileScreen
import com.dicoding.etrash.ui.screen.recycle.RecycleScreen
import com.dicoding.etrash.ui.screen.register.RegisterScreen
import com.dicoding.etrash.ui.screen.scan.CameraScreen
import com.dicoding.etrash.ui.screen.transaction.TransactionScreen
import com.dicoding.etrash.ui.screen.welcome.WelcomeScreen
import com.dicoding.etrash.ui.theme.EtrashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EtrashTheme {
                val navController = rememberNavController()
//                val viewModelFactory = ViewModelFactory()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Screen.Welcome.route) {
                        composable(Screen.Welcome.route) {
                            WelcomeScreen(navController)
                        }
                        composable(Screen.Login.route) {
                            LoginScreen(navController)
                        }
                        composable(Screen.Register.route) {
                            RegisterScreen(navController)
                        }
                        composable(Screen.Home.route) {
                            HomeScreen(navController)
                        }
                        composable(Screen.Profile.route) {
                            ProfileScreen(navController)
                        }
                        composable(Screen.Camera.route) {
                            CameraScreen(navController)
                        }
                        composable(Screen.Recycle.route) {
                            RecycleScreen(navController)
                        }
                        composable(Screen.Transaction.route) {
                            TransactionScreen(navController)
                        }
                        composable(Screen.Location.route) {
                            LocationScreen()
                        }
                        composable(Screen.PosterEtrash.route) {
                            EtrashPosterScreen()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    EtrashTheme {
        WelcomeScreen(rememberNavController())
    }
}