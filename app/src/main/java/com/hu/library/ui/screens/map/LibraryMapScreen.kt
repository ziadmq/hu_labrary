package com.hu.library.ui.screens.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hu.library.R

@Composable
fun LibraryMapScreen() {

    // ===== State for selected area ===== //
    var selectedArea by remember { mutableStateOf<MapArea?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "خريطة مكتبة الجامعة الهاشمية",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ===== Map Area ===== //
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            contentAlignment = Alignment.Center
        ) {

            // خريطة افتراضية (صورة مؤقتة)
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Library Map",
                modifier = Modifier.fillMaxSize()
            )

            // ===== Example Clickable Spots ===== //
            // رف الهندسة
            Box(
                modifier = Modifier
                    .offset(x = 80.dp, y = (-100).dp)
                    .size(60.dp)
                    .clickable {
                        selectedArea = MapArea(
                            name = "رف الهندسة",
                            description = "كتب هندسة البرمجيات، الشبكات، هندسة الحاسوب.",
                            floor = "الطابق الأول"
                        )
                    }
            )

            // قاعة دراسة 1
            Box(
                modifier = Modifier
                    .offset(x = (-40).dp, y = 50.dp)
                    .size(60.dp)
                    .clickable {
                        selectedArea = MapArea(
                            name = "قاعة دراسة 1",
                            description = "غرفة مزودة بـ 6 مقاعد للطلاب.",
                            floor = "الطابق الثاني"
                        )
                    }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ===== Selected Area Info ===== //
        selectedArea?.let { area ->
            MapAreaCard(area)
        }
    }
}

data class MapArea(
    val name: String,
    val description: String,
    val floor: String
)

@Composable
fun MapAreaCard(area: MapArea) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = area.name,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(text = area.description, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "📍 الموقع: ${area.floor}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
