package com.hu.library.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hu.library.ui.theme.*
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "مكتبة الجامعة الهاشمية",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White
                        )
                        Text(
                            "أهلاً بك، زياد 👋",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Notifications, contentDescription = "تنبيهات", tint = Color.White)
                    }
                    // Profile Image
                    AsyncImage(
                        model = "https://ui-avatars.com/api/?name=Ziad+Qafsha&background=FFC107&color=000",
                        contentDescription = "Profile",
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(38.dp)
                            .clip(CircleShape)
                            .clickable { navController.navigate("profile") }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryColor)
            )
        },
        containerColor = BackgroundColor,
        // ✅ NEW: Floating Action Button for Chat
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("chat") },
                containerColor = SecondaryColor, // Gold Color
                contentColor = Color.White,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Default.Chat, contentDescription = "المحادثة الفورية")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // 1️⃣ Auto-Sliding News Box
            NewsCarouselSection()

            Spacer(modifier = Modifier.height(24.dp))

            // 2️⃣ Library Status
            LibraryStatusSection()

            Spacer(modifier = Modifier.height(24.dp))

            // 3️⃣ Quick Services Grid (Updated with Staff)
            QuickServicesSection(navController)

            Spacer(modifier = Modifier.height(24.dp))

            // 4️⃣ New Arrivals
            SectionHeader("✨ وصلنا حديثاً", "عرض الكل")
            LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                items(5) {
                    ModernBookCard(title = "الذكاء الاصطناعي", author = "د. سعيد", status = "متوفر")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 5️⃣ Most Requested
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsCarouselSection() {
    val newsList = listOf(
        NewsItem("📢 إعلان هام", "خصم 50% على الغرامات لفترة محدودة", Color(0xFFB71C1C)),
        NewsItem("📅 فعالية", "معرض الكتاب السنوي يبدأ الأحد القادم", Color(0xFFC62828)),
        NewsItem("🕒 تنبيه", "تمديد ساعات الدوام حتى الساعة 6 مساءً", Color(0xFFD32F2F))
    )

    val pagerState = rememberPagerState(pageCount = { newsList.size })

    LaunchedEffect(pagerState.currentPage) {
        delay(5000)
        var newPosition = pagerState.currentPage + 1
        if (newPosition >= newsList.size) newPosition = 0
        pagerState.animateScrollToPage(newPosition)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp),
            pageSpacing = 16.dp
        ) { page ->
            val news = newsList[page]
            Card(
                modifier = Modifier.fillMaxWidth().height(160.dp),
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = Brush.linearGradient(colors = listOf(news.color, Color(0xFF7F0000)))),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = news.title,
                            color = SecondaryColor,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = news.description,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            repeat(newsList.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) PrimaryColor else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(if (pagerState.currentPage == iteration) 10.dp else 8.dp)
                )
            }
        }
    }
}

data class NewsItem(val title: String, val description: String, val color: Color)

@Composable
fun LibraryStatusSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatusCard("أوقات الدوام", "مفتوح الآن 🟢", "يغلق 4:00 م", Modifier.weight(1f), Color.White)
        StatusCard("مستوى الازدحام", "متوسط 🟠", "القاعة الرئيسية", Modifier.weight(1f), Color.White)
    }
}

@Composable
fun StatusCard(title: String, value: String, subValue: String, modifier: Modifier, color: Color) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(subValue, style = MaterialTheme.typography.bodySmall, color = TextSecondary)
        }
    }
}

@Composable
fun QuickServicesSection(navController: NavHostController) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text("الخدمات السريعة", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = TextPrimary)
        Spacer(modifier = Modifier.height(12.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            QuickServiceItem("🗺️", "الخريطة") { navController.navigate("map") }
            QuickServiceItem("📅", "حجز قاعة") { navController.navigate("booking") }
            QuickServiceItem("📝", "الطلبات") { navController.navigate("requests") }
            // ✅ NEW: Replaced "Chat" with "Staff" button
            QuickServiceItem("👥", "الكادر") { navController.navigate("staff") }
        }
    }
}

@Composable
fun QuickServiceItem(icon: String, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { onClick() }) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(Color.White, CircleShape)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(icon, fontSize = 26.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(label, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium, color = TextPrimary)
    }
}

@Composable
fun ModernBookCard(title: String, author: String, status: String, isAvailable: Boolean = true) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .padding(end = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(if (isAvailable) Color(0xFFFFEBEE) else Color(0xFFFAFAFA)),
                contentAlignment = Alignment.Center
            ) {
                Text("📚", fontSize = 42.sp)
                Surface(
                    modifier = Modifier.align(Alignment.TopEnd).padding(8.dp),
                    color = if (isAvailable) SuccessGreen else ErrorRed,
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(status, color = Color.White, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(title, maxLines = 1, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(author, maxLines = 1, style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, action: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold), color = TextPrimary)
        if (action.isNotEmpty()) {
            Text(action, style = MaterialTheme.typography.bodyMedium, color = ActionColor, modifier = Modifier.clickable {})
        }
    }
}