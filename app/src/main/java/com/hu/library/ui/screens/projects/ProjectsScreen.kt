package com.hu.library.ui.screens.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hu.library.data.model.Project

@Composable
fun ProjectsScreen() {

    // ===== Dummy Data (مؤقتاً) ===== //
    val projectsList = listOf(
        Project(
            id = 1,
            title = "Smart Library System",
            abstract = "This project aims to build a smart library...",
            description = "",
            supervisor = "د. محمد الرواشدة",
            students = listOf("زياد القفشة", "إياد الحباشنة"),
            department = "هندسة البرمجيات",
            year = 2024
        ),
        Project(
            id = 2,
            title = "AI-Based Book Recommender",
            abstract = "AI system that recommends books...",
            description = "",
            supervisor = "د. عمر الهزايمة",
            students = listOf("ماجد ربابعة"),
            department = "علوم الحاسوب",
            year = 2023
        )
    )

    // ===== UI ===== //
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "مشاريع التخرج",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(projectsList) { project ->
                ProjectCard(project)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ProjectCard(project: Project) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // TODO: Navigate to Project Details Screen
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = project.title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "المشرف: ${project.supervisor}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "الطلاب: ${project.students.joinToString(", ")}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "القسم: ${project.department}",
                style = MaterialTheme.typography.bodySmall
            )

            Text(
                text = "السنة: ${project.year}",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    // TODO: Navigate to details
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("عرض التفاصيل")
            }
        }
    }
}
