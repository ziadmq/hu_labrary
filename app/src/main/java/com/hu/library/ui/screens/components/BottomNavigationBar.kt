package com.hu.library.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.res.painterResource

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Map,
        BottomNavItem.Requests,
        BottomNavItem.Staff,
        BottomNavItem.Projects
    )

    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->

            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(text = item.title)
                }
            )
        }
    }
}
