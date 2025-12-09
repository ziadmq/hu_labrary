package com.hu.library.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AnnouncementSlider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(Color(0xFF00695C)),
        contentAlignment = Alignment.Center
    ) {
        Text("⬅️ إعلان… (سلايدر متحرك)", color = Color.White)
    }
}
