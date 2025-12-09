package com.hu.library.ui.screens.staff

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hu.library.data.model.Staff

@Composable
fun StaffScreen() {

    val staffList = listOf(
        Staff(
            id = 1,
            name = "د. محمد الرواشدة",
            position = "مدير المكتبة",
            department = "الإدارة",
            phone = "1065",
            email = "m.rawashdeh@hu.edu.jo",
            officeLocation = "مكتب 201 - الطابق الثاني",
            imageUrl = "https://picsum.photos/300"
        ),
        Staff(
            id = 2,
            name = "أ. رنا الحوراني",
            position = "رئيس قسم الخدمات",
            department = "خدمات المستفيدين",
            phone = "2020",
            email = "rana.h@hu.edu.jo",
            officeLocation = "مكتب 110 - الطابق الأول",
            imageUrl = "https://picsum.photos/301"
        ),
        Staff(
            id = 3,
            name = "م. فراس الخوالدة",
            position = "فهرسة وتصنيف",
            department = "تقنيات المكتبات",
            phone = "2035",
            email = "f.khawaldeh@hu.edu.jo",
            officeLocation = "مكتب 115 - الطابق الأول",
            imageUrl = "https://picsum.photos/302"
        )
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "طاقم موظفي المكتبة",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(staffList) { staff ->
                StaffCard(staff)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

@Composable
fun StaffCard(staff: Staff) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // صورة الموظف
            AsyncImage(
                model = staff.imageUrl,
                contentDescription = staff.name,
                modifier = Modifier.size(70.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(text = staff.name, style = MaterialTheme.typography.titleMedium)

                Text(
                    text = staff.position,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(text = "📧 ${staff.email}", style = MaterialTheme.typography.bodySmall)
                Text(text = "📞 ${staff.phone}", style = MaterialTheme.typography.bodySmall)
                Text(text = "📍 ${staff.officeLocation}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
