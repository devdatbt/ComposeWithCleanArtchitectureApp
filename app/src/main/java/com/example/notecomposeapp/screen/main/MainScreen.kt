package com.example.notecomposeapp.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.notecomposeapp.screen.googlemap.GoogleMapScreen
import com.example.notecomposeapp.screen.note.NoteHomeScreen
import com.example.notecomposeapp.screen.profile.AccountScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navHostController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {

    val navItemList = listOf(
        BottomNavigationItems("Home", Icons.Default.Home, 0),
        BottomNavigationItems("Maps", Icons.Default.Map, 0),
        BottomNavigationItems("Person", Icons.Default.Person, 2),
    )
    val navItems = remember { navItemList }
    val selectedIndex by viewModel.selectedIndex.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            MainBottomNavigation(
                navItems = navItems,
                selectedIndex = selectedIndex,
                onItemSelected = { viewModel.updateSelectedIndex(it) }
            )
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier.padding(innerPadding), selectedIndex, navHostController)
    }
}

@Composable
fun ContentScreen(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    navHostController: NavHostController
) {
    when (selectedIndex) {
        0 -> NoteHomeScreen(navHostController = navHostController, modifier = modifier)
        1 -> GoogleMapScreen(modifier = modifier)
        2 -> AccountScreen(navHostController = navHostController, modifier = modifier)
    }
}