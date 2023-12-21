package com.dicoding.etrash.navigation

sealed class Screen(val route: String) {
    object Home : Screen ("home")

    object Profile : Screen ("profile")
    object Login : Screen ("login")
    object Register: Screen ("register")
    object Welcome: Screen ("welcome")

    object Location: Screen ("location")
    object Recycle: Screen ("recycle")
    object Scan: Screen ("scan")
    object Camera: Screen ("camera")
    object Transaction: Screen ("transaction")

    object PosterEtrash: Screen("posteretrash")
}