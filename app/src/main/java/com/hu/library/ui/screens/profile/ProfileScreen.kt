package com.hu.library.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hu.library.data.model.User

@Composable
fun ProfileScreen() {

    // Dummy User (مؤقتاً)
    val user = User(
        id = "2020100012",
        name = "زياد القفشة",
        email = "ziad.q@hu.edu.jo",
        universityId = "2020100012",
        department = "هندسة البرمجيات",
        level = "السنة الرابعة",
        borrowedBooks = listOf(12, 45),
        fines = 3.5,
        profileImage = "https://picsum.photos/200"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ===== الصورة + الاسم =====
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = user.profileImage,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = user.name,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Divider()

        Spacer(modifier = Modifier.height(20.dp))


        // ===== المعلومات الأساسية =====
        InfoRow(title = "الرقم الجامعي:", value = user.universityId)
        InfoRow(title = "التخصص:", value = user.department)
        InfoRow(title = "السنة الدراسية:", value = user.level)

        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        // ===== الاحصائيات =====
        StatsSection(
            borrowedCount = user.borrowedBooks.size,
            fines = user.fines
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ===== زر تعديل =====
        Button(
            onClick = {
                // TODO: Go to Edit Profile Screen
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("تعديل البيانات")
        }
    }
}


@Composable
fun InfoRow(title: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
fun StatsSection(borrowedCount: Int, fines: Double) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("📚", fontSize = MaterialTheme.typography.headlineMedium.fontSize)
            Text("كتب مستعارة", fontWeight = FontWeight.Medium)
            Text("$borrowedCount", fontWeight = FontWeight.Bold)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("💰", fontSize = MaterialTheme.typography.headlineMedium.fontSize)
            Text("غرامات", fontWeight = FontWeight.Medium)
            Text("$fines د.أ", fontWeight = FontWeight.Bold, color = Color.Red)
        }
    }
}
