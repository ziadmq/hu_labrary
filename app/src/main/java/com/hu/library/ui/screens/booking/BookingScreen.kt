package com.hu.library.ui.screens.booking

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.library.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen() {

    // Data
    val halls = listOf("قاعة القدس", "قاعة الكرامة", "مسرح 1", "مسرح 2")
    val rooms = listOf("غرفة 101", "غرفة 102", "غرفة 103", "غرفة 205", "غرفة 206", "غرفة 207")
    val activityTypes = listOf("دراسة جماعية", "مناقشة مشروع", "اجتماع", "ورشة عمل")

    // State
    var selectedType by remember { mutableStateOf("قاعة") } // "قاعة" or "غرفة"
    var selectedOption by remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf("") }
    var timeFrom by remember { mutableStateOf("") }
    var timeTo by remember { mutableStateOf("") }
    var selectedActivity by remember { mutableStateOf(activityTypes[0]) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("حجز القاعات", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundColor,
                    titleContentColor = TextPrimary
                )
            )
        },
        containerColor = BackgroundColor,
        bottomBar = {
            // Submit Button Area
            Surface(
                color = Color.White,
                shadowElevation = 8.dp,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Button(
                    onClick = { /* TODO: Submit */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("تأكيد الحجز", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            // 1️⃣ Custom Segmented Control (Toggle)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color(0xFFEEEEEE), RoundedCornerShape(12.dp))
                    .padding(4.dp)
            ) {
                val types = listOf("قاعة", "غرفة")
                types.forEach { type ->
                    val isSelected = selectedType == type
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                            .background(if (isSelected) PrimaryColor else Color.Transparent)
                            .clickable {
                                selectedType = type
                                selectedOption = "" // Reset selection
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (type == "قاعة") "قاعات ومسارح" else "غرف دراسية",
                            color = if (isSelected) Color.White else TextSecondary,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 2️⃣ Grid of Options
            Text(
                text = "اختر المكان المناسب:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            val itemsToShow = if (selectedType == "قاعة") halls else rooms
            val icon = if (selectedType == "قاعة") Icons.Default.DateRange else Icons.Default.Notifications

            // Using FlowRow-like behavior with Grid inside Column requires fixed height or careful placement.
            // Since we are in a vertical scroll, let's use a fixed height grid or just custom rows.
            // For simplicity in this layout, we render a non-scrolling grid:

            NonScrollGrid(items = itemsToShow, columns = 2) { item ->
                BookingCard(
                    name = item,
                    icon = icon,
                    isSelected = selectedOption == item,
                    onClick = { selectedOption = item }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 3️⃣ Date & Time Form
            Text(
                text = "تفاصيل الوقت:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            BookingTextField(
                value = selectedDate,
                onValueChange = { selectedDate = it },
                label = "التاريخ",
                icon = Icons.Default.DateRange,
                placeholder = "2025-01-01"
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Box(modifier = Modifier.weight(1f)) {
                    BookingTextField(
                        value = timeFrom,
                        onValueChange = { timeFrom = it },
                        label = "من",
                        icon = Icons.Default.Notifications,
                        placeholder = "09:00"
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    BookingTextField(
                        value = timeTo,
                        onValueChange = { timeTo = it },
                        label = "إلى",
                        icon = Icons.Default.Face,
                        placeholder = "11:00"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 4️⃣ Activity Type (Dropdown Style)
            Text(
                text = "نوع النشاط:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            ActivityTypeSelector(
                options = activityTypes,
                selected = selectedActivity,
                onSelected = { selectedActivity = it }
            )

            Spacer(modifier = Modifier.height(100.dp)) // Space for bottom button
        }
    }
}

// ===== Components ===== //

@Composable
fun BookingCard(name: String, icon: androidx.compose.ui.graphics.vector.ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(4.dp)
            .clickable { onClick() }
            .border(
                width = if (isSelected) 2.dp else 0.dp,
                color = if (isSelected) SecondaryColor else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFFFF8E1) else Color.White // Light Gold if selected
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) PrimaryColor else TextSecondary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) PrimaryColor else TextPrimary
            )
        }
    }
}

@Composable
fun BookingTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    placeholder: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder, color = Color.LightGray) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = PrimaryColor) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = PrimaryColor
        ),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityTypeSelector(options: List<String>, selected: String, onSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, null) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(), // Required for Material3 dropdown
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryColor,
                unfocusedBorderColor = Color.LightGray
            )
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

// Simple Helper for Grid Layout without scrolling issues inside Column
@Composable
fun NonScrollGrid(items: List<String>, columns: Int, content: @Composable (String) -> Unit) {
    val rows = (items.size + columns - 1) / columns
    Column {
        for (i in 0 until rows) {
            Row(Modifier.fillMaxWidth()) {
                for (j in 0 until columns) {
                    val index = i * columns + j
                    Box(Modifier.weight(1f)) {
                        if (index < items.size) {
                            content(items[index])
                        }
                    }
                }
            }
        }
    }
}