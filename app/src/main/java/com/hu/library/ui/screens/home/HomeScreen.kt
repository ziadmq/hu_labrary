package com.hu.library.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hu.library.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("مكتبة الجامعة الهاشمية", style = MaterialTheme.typography.titleMedium, color = Color.White)
                        Text("أهلاً بك، زياد 👋", style = MaterialTheme.typography.labelSmall, color = Color.LightGray)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Notifications, contentDescription = "تنبيهات", tint = Color.White)
                    }
                    // صورة البروفايل (تأكد من وجود إذن الإنترنت)
                    AsyncImage(
                        model = "https://ui-avatars.com/api/?name=Ziad+Qafsha&background=random",
                        contentDescription = "Profile",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(36.dp)
                            .clip(CircleShape)
                            .clickable { navController.navigate("profile") }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(BackgroundColor)
        ) {
            // 1️⃣ سلايدر الإعلانات (Modern Gradient)
            ModernAnnouncementSlider()

            Spacer(modifier = Modifier.height(16.dp))

            // 2️⃣ حالة المكتبة والدوام
            LibraryStatusSection()

            Spacer(modifier = Modifier.height(24.dp))

            // 3️⃣ الخدمات السريعة (Grid)
            QuickServicesSection(navController)

            Spacer(modifier = Modifier.height(24.dp))

            // 4️⃣ وصلنا حديثاً (كتب)
            SectionHeader("✨ وصلنا حديثاً", "عرض الكل")
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                items(5) {
                    ModernBookCard(title = "الذكاء الاصطناعي", author = "د. سعيد", status = "متوفر")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5️⃣ أحدث الإضافات
            SectionHeader("📚 الكتب الأكثر طلباً", "")
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                items(5) {
                    ModernBookCard(title = "Kotlin for Android", author = "Google", status = "معار", isAvailable = false)
                }
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

// --- Composable Components ---

@Composable
fun ModernAnnouncementSlider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(PrimaryColor, Color(0xFF1E293B))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("📢 إعلان هام", color = SecondaryColor, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "تمديد ساعات الدوام خلال الامتحانات",
                color = Color.White,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun LibraryStatusSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // بطاقة الدوام
        StatusCard(
            title = "أوقات الدوام",
            value = "مفتوح الآن 🟢",
            subValue = "يغلق 4:00 م",
            modifier = Modifier.weight(1f),
            color = Color.White
        )
        // بطاقة الازدحام
        StatusCard(
            title = "مستوى الازدحام",
            value = "متوسط 🟠",
            subValue = "القاعة الرئيسية",
            modifier = Modifier.weight(1f),
            color = Color.White
        )
    }
}

@Composable
fun StatusCard(title: String, value: String, subValue: String, modifier: Modifier, color: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.labelMedium, color = TextSecondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(subValue, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
        }
    }
}

@Composable
fun QuickServicesSection(navController: NavHostController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("الخدمات السريعة", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            QuickServiceItem("🗺️", "الخريطة") { navController.navigate("map") }
            QuickServiceItem("📅", "حجز قاعة") { navController.navigate("booking") }
            QuickServiceItem("📝", "الطلبات") { navController.navigate("requests") }
            QuickServiceItem("💬", "محادثة") { navController.navigate("chat") }
        }
    }
}

@Composable
fun QuickServiceItem(icon: String, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { onClick() }) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(Color.White, CircleShape)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun ModernBookCard(title: String, author: String, status: String, isAvailable: Boolean = true) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
                    .background(if (isAvailable) Color(0xFFE2E8F0) else Color(0xFFF1F5F9)),
                contentAlignment = Alignment.Center
            ) {
                Text("📚", fontSize = 40.sp) // صورة افتراضية

                // Badge الحالة
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    color = if (isAvailable) SuccessGreen else ErrorRed,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        text = status,
                        color = Color.White,
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(title, maxLines = 1, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(author, maxLines = 1, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, action: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
        if (action.isNotEmpty()) {
            Text(action, style = MaterialTheme.typography.bodyMedium, color = BlueAccent, modifier = Modifier.clickable {})
        }
    }
}