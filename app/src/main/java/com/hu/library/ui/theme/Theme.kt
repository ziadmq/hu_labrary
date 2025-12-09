package com.hu.library.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    headlineLarge = androidx.compose.ui.text.TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),
    headlineMedium = androidx.compose.ui.text.TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    ),
    titleMedium = androidx.compose.ui.text.TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodyLarge = androidx.compose.ui.text.TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = androidx.compose.ui.text.TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = androidx.compose.ui.text.TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Light
    )
)
