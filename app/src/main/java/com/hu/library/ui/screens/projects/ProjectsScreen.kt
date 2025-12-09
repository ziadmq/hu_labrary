package com.hu.library.ui.screens.projects

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hu.library.data.model.Project
import com.hu.library.ui.theme.PrimaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen() {

    // فلاتر
    val filters = listOf("الكل", "هندسة البرمجيات", "علوم الحاسوب", "نظم المعلومات", "رسائل ماجستير")
    var selectedFilter by remember { mutableStateOf("الكل") }

    // بيانات وهمية
    val projectsList = listOf(
        Project(1, "نظام المكتبة الذكي", "ملخص...", "", "د. محمد", listOf("زياد"), "هندسة البرمجيات", 2024),
        Project(2, "AI Image Gen", "ملخص...", "", "د. علي", listOf("أحمد"), "علوم الحاسوب", 2023)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("أرشيف المشاريع والرسائل", style = MaterialTheme.typography.headlineMedium, color = PrimaryColor)

        Spacer(modifier = Modifier.height(16.dp))

        // شريط الفلترة
        LazyRow {
            items(filters) { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    label = { Text(filter) },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(projectsList) { project ->
                ProjectCardModern(project)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ProjectCardModern(project: Project) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(project.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("👨‍🎓 ${project.students.joinToString("، ")}", style = MaterialTheme.typography.bodyMedium)
            Text("👨‍🏫 المشرف: ${project.supervisor}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                SuggestionChip(onClick = {}, label = { Text(project.department) })
                SuggestionChip(onClick = {}, label = { Text("${project.year}") })
            }

            Button(onClick = {}, modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
                Text("عرض التفاصيل والملفات")
            }
        }
    }
}