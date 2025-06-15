package com.example.notecomposeapp.screen.main

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.notecomposeapp.theme.MyAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainBottomNavigation(
    navItems: List<BottomNavigationItems>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.wrapContentHeight(),
        containerColor = MyAppTheme.color.grayColor
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MyAppTheme.color.whiteColor,
                    unselectedIconColor = MyAppTheme.color.blackColor,
                    selectedTextColor = MyAppTheme.color.blackColor,
                    indicatorColor = MyAppTheme.color.lightBlueColor
                ),
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) },
                icon = {
                    BadgedBox(badge = {
                        if (item.badgeCount > 0)
                            Badge() {
                                Text(text = item.badgeCount.toString())
                            }
                    }) {
                        Icon(imageVector = item.icon, contentDescription = "Icon")
                    }

                },
                label = { Text(text = item.label) }
            )
        }
    }
}