package com.example.notecomposeapp.screen.main

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItems(
    val label : String,
    val icon : ImageVector,
    val badgeCount : Int,
)