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

        // 🏠 الرئيسية
        composable("home") {
            HomeScreen(navController)
        }

        // 🗺️ الخريطة
        composable("map") {
            LibraryMapScreen()
        }

        // 📄 الطلبات
        composable("requests") {
            RequestsScreen()
        }

        // 👥 الموظفين
        composable("staff") {
            StaffScreen()
        }

        // 🎓 المشاريع
        composable("projects") {
            ProjectsScreen()
        }
    }
}