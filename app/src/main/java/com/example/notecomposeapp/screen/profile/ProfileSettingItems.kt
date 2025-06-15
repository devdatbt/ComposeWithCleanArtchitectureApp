package com.example.notecomposeapp.screen.profile

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ProfileSettingItems(
    val title: String,
    val iconLeft: ImageVector,
    val iconRight: ImageVector,
    val colorGradientBackground: List<Color>
) {
    object SettingItem :
        ProfileSettingItems(
            "Setting", Icons.Filled.Settings, Icons.Filled.KeyboardArrowRight, listOf(
                Color(0xFFF57C00),
                Color(0xFFFFA000),
                Color(0xFFFFC73A)
            )
        )

    object NotificationItem :
        ProfileSettingItems(
            "Notification", Icons.Filled.Notifications, Icons.Filled.KeyboardArrowRight,
            listOf(
                Color(0xFF2196F3),
                Color(0xFF45A9F8),
                Color(0xFF7DC2F8)
            )
        )

    object LogoutItem :
        ProfileSettingItems(
            "Log out", Icons.Filled.Logout, Icons.Filled.KeyboardArrowRight, listOf(
                Color(0xFF0097A7),
                Color(0xED14AEBE),
                Color(0xED59D7E4)
            )
        )
}