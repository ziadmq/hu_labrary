package com.hu.library.ui.screens.requests

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import com.hu.library.R

@Composable
fun RequestsScreen() {

    val requestsList = listOf(
        RequestOption("طلب شراء كتاب عربي", R.drawable.ic_launcher_background),
        RequestOption("طلب شراء كتاب أجنبي", R.drawable.ic_launcher_background),
        RequestOption("اقتراح إضافة كتاب", R.drawable.ic_launcher_background),
        RequestOption("طلب مقالة", R.drawable.ic_launcher_background),
        RequestOption("إهداء كتاب للمكتبة", R.drawable.ic_launcher_background)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "مركز الطلبات",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(requestsList.size) { index ->
                RequestItemCard(requestsList[index])
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

data class RequestOption(
    val title: String,
    val icon: Int
)

@Composable
fun RequestItemCard(option: RequestOption) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // TODO: Open respective request form screen
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = option.icon),
                contentDescription = option.title,
                modifier = Modifier.size(36.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = option.title,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
