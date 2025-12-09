package com.hu.library.ui.components

import androidx.annotation.DrawableRes
import com.hu.library.R

sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomNavItem("home", "الرئيسية", R.drawable.ic_launcher_background)
    object Map : BottomNavItem("map", "الخريطة", R.drawable.ic_launcher_background)
    object Requests : BottomNavItem("requests", "الطلبات", R.drawable.ic_launcher_background)
    object Staff : BottomNavItem("staff", "الموظفين", R.drawable.ic_launcher_background)
    object Projects : BottomNavItem("projects", "المشاريع", R.drawable.ic_launcher_background)
}
