package com.hu.library.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hu.library.ui.screens.home.HomeScreen
import com.hu.library.ui.screens.map.LibraryMapScreen
import com.hu.library.ui.screens.requests.RequestsScreen
import com.hu.library.ui.screens.staff.StaffScreen
import com.hu.library.ui.screens.projects.ProjectsScreen
import com.hu.library.ui.screens.profile.ProfileScreen
import com.hu.library.ui.screens.booking.BookingScreen
import com.hu.library.ui.screens.chat.ChatScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") { HomeScreen(navController) }
        composable("map") { LibraryMapScreen() }
        composable("requests") { RequestsScreen() }
        composable("staff") { StaffScreen(navController) }
        composable("projects") { ProjectsScreen() }

        // المسارات الجديدة
        composable("profile") { ProfileScreen() }
        composable("chat") { ChatScreen() }
        composable("booking") { BookingScreen() }
    }
}