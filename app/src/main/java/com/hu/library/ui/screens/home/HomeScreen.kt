package com.hu.library.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hu.library.R
import com.hu.library.ui.components.*

@Composable
fun HomeScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()

    // Dummy data (مؤقتاً)
    val newArrivals = listOf(
        DemoBook("Effective Java", "Joshua Bloch"),
        DemoBook("Kotlin in Action", "Dmitry Jemerov"),
        DemoBook("AI Basics", "Dr. Saeed")
    )

    val latestBooks = newArrivals + newArrivals

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        // 🔰 Logo + Title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "HU Logo",
                modifier = Modifier.size(55.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "مكتبة الجامعة الهاشمية",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔔 Announcement Slider
        AnnouncementSlider()

        Spacer(modifier = Modifier.height(16.dp))

        // 🔍 Search Bar
        SearchBar(
            hint = "ابحث عن كتاب…",
            onSearch = { /* TODO */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // 📗 New Arrivals Section
        Text(
            text = "وصلنا حديثًا",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            items(newArrivals) { book ->
                BookCard(
                    title = book.title,
                    author = book.author
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 📘 Latest Additions Section
        Text(
            text = "أحدث الإضافات",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow {
            items(latestBooks) { book ->
                BookCard(
                    title = book.title,
                    author = book.author
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🕒 Working Hours
        WorkingHoursSection()

        Spacer(modifier = Modifier.height(20.dp))

        // 📈 Crowd Level Section
        CrowdLevelSection()

        Spacer(modifier = Modifier.height(20.dp))

        // 🎉 Upcoming Holidays
        UpcomingHolidaysSection()

        Spacer(modifier = Modifier.height(50.dp))
    }
}
@Composable
fun WorkingHoursSection() {
    Column {
        Text("🕒 أوقات الدوام:", fontWeight = FontWeight.Bold)
        Text("الأحد - الخميس: 8:00 صباحًا - 4:00 مساءً")
        Text("الجمعة والسبت: عطلة")
    }
}

@Composable
fun CrowdLevelSection() {
    Column {
        Text("📈 مستوى الازدحام:", fontWeight = FontWeight.Bold)
        Text("حاليًا: متوسط")
    }
}

@Composable
fun UpcomingHolidaysSection() {
    Column {
        Text("🎉 العطل القادمة:", fontWeight = FontWeight.Bold)
        Text("رأس السنة الميلادية - 1/1")
        Text("عيد الاستقلال - 25/5")
    }
}


data class DemoBook(val title: String, val author: String)
