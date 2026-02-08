package com.hu.library.ui.screens.map

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.library.R
import com.hu.library.ui.theme.*

@Composable
fun LibraryMapScreen() {

    // ===== State ===== //
    var selectedFloor by remember { mutableStateOf(1) }
    var selectedLocation by remember { mutableStateOf<MapLocation?>(null) }

    // Dummy Locations (You can fetch these from API later)
    val locations = listOf(
        MapLocation("رف الهندسة", "كتب هندسة البرمجيات والشبكات", 1, 80.dp, (-50).dp, MapType.Books),
        MapLocation("قاعة المراجع", "القواميس والموسوعات", 1, (-80).dp, (-80).dp, MapType.Books),
        MapLocation("قاعة دراسة 1", "غرفة هادئة للدراسة (6 مقاعد)", 2, (-40).dp, 50.dp, MapType.Room),
        MapLocation("مكتب الإعارة", "استعارة وإرجاع الكتب", 1, 0.dp, 100.dp, MapType.Service)
    )

    // Filter locations by floor
    val currentFloorLocations = locations.filter { it.floor == selectedFloor }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            // 1️⃣ Header
            Text(
                text = "خريطة المكتبة 🗺️",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = TextPrimary
            )
            Text(
                text = "اضغط على النقاط لمعرفة التفاصيل",
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 2️⃣ Floor Selector (Modern Tabs)
            FloorSelector(selectedFloor) { selectedFloor = it }

            Spacer(modifier = Modifier.height(16.dp))

            // 3️⃣ The Interactive Map
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Fill remaining space
                    .clip(RoundedCornerShape(24.dp))
                    .border(2.dp, Color(0xFFE0E0E0), RoundedCornerShape(24.dp))
                    .background(Color.White), // White background for the map
                contentAlignment = Alignment.Center
            ) {

                // Placeholder Map Image (Replace with actual floor plan images)
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your map image
                    contentDescription = "Map",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.3f), // Faded background
                    contentScale = ContentScale.Crop
                )

                // Grid Lines Effect (Optional for technical look)
                // You can add a Canvas here to draw grid lines if you want

                // 📍 Render Pins
                currentFloorLocations.forEach { loc ->
                    MapPin(
                        location = loc,
                        isSelected = selectedLocation == loc,
                        onClick = { selectedLocation = loc }
                    )
                }
            }

            // Spacer for Bottom Sheet
            Spacer(modifier = Modifier.height(100.dp))
        }

        // 4️⃣ Bottom Detail Card (Overlay)
        AnimatedVisibility(
            visible = selectedLocation != null,
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            selectedLocation?.let { loc ->
                LocationDetailCard(
                    location = loc,
                    onClose = { selectedLocation = null }
                )
            }
        }
    }
}

// ===== Components ===== //

@Composable
fun FloorSelector(selectedFloor: Int, onFloorSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color(0xFFEEEEEE), RoundedCornerShape(16.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf(1, 2, 3).forEach { floor ->
            val isSelected = selectedFloor == floor
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) PrimaryColor else Color.Transparent)
                    .clickable { onFloorSelected(floor) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "الطابق $floor",
                    color = if (isSelected) Color.White else TextSecondary,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun MapPin(location: MapLocation, isSelected: Boolean, onClick: () -> Unit) {
    // Animate pin size when selected
    val size = if (isSelected) 50.dp else 40.dp
    val iconColor = if (location.type == MapType.Books) PrimaryColor else SecondaryColor

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .offset(x = location.x, y = location.y)
            .clickable(onClick = onClick)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = location.name,
            tint = iconColor,
            modifier = Modifier
                .size(size)
                .shadow(if (isSelected) 8.dp else 0.dp, CircleShape)
        )
        if (isSelected) {
            Box(
                modifier = Modifier
                    .background(TextPrimary, RoundedCornerShape(4.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(location.name, color = Color.White, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun LocationDetailCard(location: MapLocation, onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(16.dp, RoundedCornerShape(24.dp)),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Icon Box
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(PrimaryColor.copy(alpha = 0.1f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when(location.type) {
                                MapType.Books -> "📚"
                                MapType.Room -> "🎓"
                                MapType.Service -> "💁‍♂️"
                            },
                            fontSize = 24.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(location.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Text("الطابق ${location.floor}", style = MaterialTheme.typography.labelMedium, color = SecondaryColor)
                    }
                }

                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(location.description, style = MaterialTheme.typography.bodyMedium, color = TextSecondary)

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* Navigate or show path */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)
            ) {
                Text("عرض المسار", color = Color.White)
            }
        }
    }
}

// ===== Helper Utility ===== //
fun Modifier.alpha(alpha: Float) = this.then(Modifier.drawWithContent {
    drawContent()
    drawRect(Color.White.copy(alpha = 1f - alpha)) // Simple fade simulation
})

// Data Models
data class MapLocation(
    val name: String,
    val description: String,
    val floor: Int,
    val x: androidx.compose.ui.unit.Dp,
    val y: androidx.compose.ui.unit.Dp,
    val type: MapType
)

enum class MapType {
    Books, Room, Service
}