package com.hu.library.ui.screens.booking

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BookingScreen() {

    val halls = listOf("قاعة 1", "قاعة 2", "قاعة 3", "قاعة 4")
    val rooms = listOf("غرفة 1", "غرفة 2", "غرفة 3", "غرفة 4", "غرفة 5", "غرفة 6")
    val activityTypes = listOf("دراسة جماعية", "مناقشة مشروع", "اجتماع", "ورشة عمل")

    var selectedType by remember { mutableStateOf("قاعة") }
    var selectedRoom by remember { mutableStateOf("") }
    var selectedActivity by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf("") }
    var timeFrom by remember { mutableStateOf("") }
    var timeTo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "حجز القاعات والغرف",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // اختيار النوع (قاعة / غرفة)
        Text("نوع الحجز:", fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(6.dp))

        Row {
            FilterChip(
                selected = selectedType == "قاعة",
                onClick = { selectedType = "قاعة" },
                label = { Text("قاعة") },
                modifier = Modifier.padding(end = 8.dp)
            )
            FilterChip(
                selected = selectedType == "غرفة",
                onClick = { selectedType = "غرفة" },
                label = { Text("غرفة") }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // اختيار قاعة أو غرفة
        Text("اختر ${selectedType}:", fontWeight = FontWeight.Medium)

        Spacer(modifier = Modifier.height(6.dp))

        LazyColumn {
            items(if (selectedType == "قاعة") halls else rooms) { item ->
                BookingOptionCard(
                    name = item,
                    isSelected = selectedRoom == item,
                    onClick = { selectedRoom = item }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // اختيار التاريخ
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { selectedDate = it },
            label = { Text("التاريخ (مثال: 2025-01-12)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // الوقت - من
        OutlinedTextField(
            value = timeFrom,
            onValueChange = { timeFrom = it },
            label = { Text("من (HP)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // الوقت - إلى
        OutlinedTextField(
            value = timeTo,
            onValueChange = { timeTo = it },
            label = { Text("إلى (HP)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // نوع النشاط
        DropdownMenuSelector(
            label = "نوع النشاط",
            options = activityTypes,
            selectedOption = selectedActivity,
            onOptionSelected = { selectedActivity = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // زر إرسال الطلب
        Button(
            onClick = {
                // TODO: Submit to API
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("إرسال طلب الحجز")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// بطاقة خيار (قاعة / غرفة)
@Composable
fun BookingOptionCard(name: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

// Dropdown Component
@Composable
fun DropdownMenuSelector(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "فتح")
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onOptionSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}
