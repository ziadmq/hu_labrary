package com.hu.library.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BookCard(title: String, author: String) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .height(120.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(title)
            Text(author, color = Color.Gray)
        }
    }
}
