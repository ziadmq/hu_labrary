package com.hu.library.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hu.library.ui.theme.*

@Composable
fun ProfileScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(scrollState)
    ) {
        // 1️⃣ Profile Header with ID Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp) // Tall enough for the overlap
        ) {
            // The Red Curve Background
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(PrimaryColor, PrimaryVariant)
                        )
                    )
            )

            // Header Title
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "الملف الشخصي",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            // The Floating "Student ID" Card
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .height(180.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Avatar with Gold Border
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .border(3.dp, SecondaryColor, CircleShape)
                            .padding(3.dp)
                            .clip(CircleShape)
                    ) {
                        AsyncImage(
                            model = "https://ui-avatars.com/api/?name=Ziad+Qafsha&background=B71C1C&color=fff&size=200",
                            contentDescription = "Profile",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    // User Info
                    Column {
                        Text(
                            text = "زياد قفشة",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "هندسة البرمجيات",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // ID Chip
                        Surface(
                            color = BackgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "ID: 2135099",
                                style = MaterialTheme.typography.labelMedium,
                                color = PrimaryColor,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 2️⃣ Quick Stats Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileStatCard("الكتب المستعارة", "5", Icons.Default.MenuBook, Color(0xFFE3F2FD), PrimaryColor)
            ProfileStatCard("الغرامات", "0.0 JD", Icons.Default.AttachMoney, Color(0xFFFFEBEE), Color.Red)
            ProfileStatCard("النقاط", "120", Icons.Default.Star, Color(0xFFFFF8E1), SecondaryColor)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 3️⃣ Settings / Menu List
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                "الإعدادات العامة",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(12.dp))

            ProfileMenuItem(icon = Icons.Default.Person, title = "تعديل البيانات", subtitle = "تحديث رقم الهاتف وكلمة السر") {}
            ProfileMenuItem(icon = Icons.Default.History, title = "سجل الاستعارات", subtitle = "عرض الكتب السابقة") {}
            ProfileMenuItem(icon = Icons.Default.Notifications, title = "الإشعارات", subtitle = "تنبيهات الإرجاع والعروض") {}
            ProfileMenuItem(icon = Icons.Default.Language, title = "اللغة", subtitle = "العربية (الأردن)") {}

            Spacer(modifier = Modifier.height(16.dp))

            // Logout Button
            Button(
                onClick = { /* Logout Logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFEBEE)),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(0.dp)
            ) {
                Icon(Icons.Default.Logout, contentDescription = null, tint = ErrorRed)
                Spacer(modifier = Modifier.width(8.dp))
                Text("تسجيل الخروج", color = ErrorRed, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

// ===== Components =====

@Composable
fun ProfileStatCard(label: String, value: String, icon: ImageVector, bgColor: Color, iconColor: Color) {
    Card(
        modifier = Modifier
            .width(105.dp) // Fixed width for alignment
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(label, style = MaterialTheme.typography.labelSmall, color = TextSecondary, fontSize = 10.sp)
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(BackgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = PrimaryColor)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
                Text(subtitle, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            }

            Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = TextSecondary)
        }
    }
}