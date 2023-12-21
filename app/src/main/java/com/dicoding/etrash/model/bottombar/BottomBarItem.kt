package com.dicoding.etrash.model.bottombar

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val label: String,
    val route: String,
    val onClick: () -> Unit
)