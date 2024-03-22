package com.example.notecomposeapp.ext

import androidx.navigation.NavHostController


fun NavHostController.popUp() {
    this.popBackStack()
}

fun NavHostController.navigateWith(route: String) {
    this.navigate(route) { launchSingleTop = true }
}

fun NavHostController.navigateAndPopUp(route: String, popUp: String) {
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(popUp) { inclusive = true }
    }
}

fun NavHostController.clearAndNavigate(route: String) {
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(0) { inclusive = true }
    }
}
