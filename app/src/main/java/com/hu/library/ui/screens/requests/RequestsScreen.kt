package com.hu.library.ui.screens.requests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.library.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestsScreen() {
    // حالة للتحكم في النماذج المختلفة
    var showSuggestionSheet by remember { mutableStateOf(false) }
    var showPurchaseSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val requestsList = listOf(
        RequestOption("شراء كتاب عربي", "اطلب توفير كتاب", Icons.Default.MenuBook, Color(0xFFE3F2FD), Color(0xFF1565C0)),
        RequestOption("شراء كتاب أجنبي", "مراجع عالمية", Icons.Default.Language, Color(0xFFF3E5F5), Color(0xFF7B1FA2)),
        RequestOption("اقتراح كتاب", "أضف عنواناً جديداً", Icons.Default.Lightbulb, Color(0xFFFFF8E1), Color(0xFFFBC02D)),
        RequestOption("طلب مقالة", "أبحاث ومجلات", Icons.Default.Article, Color(0xFFE8F5E9), Color(0xFF2E7D32)),
        RequestOption("إهداء للمكتبة", "تبرع بكتبك", Icons.Default.VolunteerActivism, Color(0xFFFFEBEE), Color(0xFFC62828)),
        RequestOption("إبلاغ عن مشكلة", "صيانة أو شكوى", Icons.Default.ReportProblem, Color(0xFFECEFF1), Color(0xFF455A64))
    )

    val recentRequests = listOf(
        RequestStatus("شراء كتاب: Java Programming", "قيد المراجعة ⏳", WarningYellow),
        RequestStatus("حجز غرفة دراسية (102)", "تم القبول ✅", SuccessGreen)
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("مركز الخدمات", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BackgroundColor,
                    titleContentColor = TextPrimary
                )
            )
        },
        containerColor = BackgroundColor
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "طلباتي السابقة",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            recentRequests.forEach { status ->
                RecentRequestCard(status)
                Spacer(modifier = Modifier.height(10.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "تقديم طلب جديد",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(requestsList) { item ->
                    ServiceCard(item) {
                        // تحديد أي نموذج يفتح بناءً على الخيار المختار
                        when (item.title) {
                            "اقتراح كتاب" -> showSuggestionSheet = true
                            "شراء كتاب عربي" -> showPurchaseSheet = true
                        }
                    }
                }
                item { Spacer(modifier = Modifier.height(20.dp)) }
            }
        }

        // --- نافذة نموذج اقتراح كتاب ---
        if (showSuggestionSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSuggestionSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
                dragHandle = { BottomSheetDefaults.DragHandle(color = PrimaryColor.copy(0.2f)) }
            ) {
                BookSuggestionForm(onDismiss = { showSuggestionSheet = false })
            }
        }

        // --- نافذة نموذج شراء كتاب عربي (الهيئة التدريسية) ---
        if (showPurchaseSheet) {
            ModalBottomSheet(
                onDismissRequest = { showPurchaseSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
                dragHandle = { BottomSheetDefaults.DragHandle(color = PrimaryColor.copy(0.2f)) }
            ) {
                ArabicBookPurchaseForm(onDismiss = { showPurchaseSheet = false })
            }
        }
    }
}

@Composable
fun ArabicBookPurchaseForm(onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var pubPlace by remember { mutableStateOf("") }
    var editionYear by remember { mutableStateOf("") }
    var copies by remember { mutableStateOf("") }
    var source by remember { mutableStateOf("") }
    var purpose by remember { mutableStateOf("مرجع") } // مرجع أو تدريسي

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("طلب شراء كتب عربية", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = PrimaryColor)
                Text("مخصص لأعضاء الهيئة التدريسية", fontSize = 12.sp, color = Color.Gray)
            }
            IconButton(onClick = onDismiss) { Icon(Icons.Rounded.Close, null) }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // تنبيه الشرط
        Surface(color = Color(0xFFFFF3E0), shape = RoundedCornerShape(12.dp), modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.Info, null, tint = Color(0xFFE65100), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("الشرط: يشترط كتاب تغطية من عميد الكلية.", fontSize = 12.sp, color = Color(0xFFE65100), fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text("معلومات الكتاب :", fontWeight = FontWeight.Bold, color = TextPrimary)

        ModernTextField(title, { title = it }, "عنوان الكتاب")
        ModernTextField(author, { author = it }, "المؤلف")
        ModernTextField(publisher, { publisher = it }, "الناشر")
        ModernTextField(pubPlace, { pubPlace = it }, "مكان النشر")
        ModernTextField(editionYear, { editionYear = it }, "الطبعة وسنة النشر")
        ModernTextField(copies, { copies = it }, "عدد النسخ")

        Spacer(modifier = Modifier.height(16.dp))
        Text("أين وجدت هذا الكتاب :", fontWeight = FontWeight.Bold, color = TextPrimary)
        ModernTextField(source, { source = it }, "المصدر / الموقع")

        Spacer(modifier = Modifier.height(16.dp))
        Text("الغرض من طلب الكتاب :", fontWeight = FontWeight.Bold, color = TextPrimary)

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = purpose == "مرجع", onClick = { purpose = "مرجع" })
            Text("مرجع (لأغراض البحث)", modifier = Modifier.clickable { purpose = "مرجع" }, fontSize = 14.sp)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = purpose == "تدريسي", onClick = { purpose = "تدريسي" })
            Text("تدريسي (معتمد لتدريس المقرر)", modifier = Modifier.clickable { purpose = "تدريسي" }, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { /* تنفيذ */ }, modifier = Modifier.fillMaxWidth().height(55.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
            Text("إرسال الطلب الرسمي", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BookSuggestionForm(onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var publisher by remember { mutableStateOf("") }
    var pubPlace by remember { mutableStateOf("") }
    var editionYear by remember { mutableStateOf("") }
    var foundSource by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth().padding(24.dp).verticalScroll(rememberScrollState()).padding(bottom = 24.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("نموذج اقتراح كتاب", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = PrimaryColor)
            IconButton(onClick = onDismiss) { Icon(Icons.Rounded.Close, null) }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("معلومات الكتاب :", fontWeight = FontWeight.Bold, color = TextPrimary)
        ModernTextField(title, { title = it }, "عنوان الكتاب")
        ModernTextField(author, { author = it }, "المؤلف")
        ModernTextField(publisher, { publisher = it }, "الناشر")
        ModernTextField(pubPlace, { pubPlace = it }, "مكان النشر")
        ModernTextField(editionYear, { editionYear = it }, "الطبعة وسنة النشر")
        Spacer(modifier = Modifier.height(16.dp))
        Text("أين وجدت هذا الكتاب :", fontWeight = FontWeight.Bold, color = TextPrimary)
        ModernTextField(foundSource, { foundSource = it }, "المصدر / الرابط")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { /* تنفيذ */ }, modifier = Modifier.fillMaxWidth().height(55.dp), shape = RoundedCornerShape(16.dp), colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor)) {
            Text("إرسال الاقتراح", fontWeight = FontWeight.Bold)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = PrimaryColor,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = PrimaryColor
        ),
        singleLine = true
    )
}

@Composable
fun ServiceCard(option: RequestOption, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().height(135.dp).clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier.size(45.dp).clip(CircleShape).background(option.bgTint), contentAlignment = Alignment.Center) {
                Icon(imageVector = option.icon, contentDescription = null, tint = option.iconTint, modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = option.title, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold, color = TextPrimary, textAlign = TextAlign.Center)
            Text(text = option.subtitle, style = MaterialTheme.typography.labelSmall, color = TextSecondary, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun RecentRequestCard(status: RequestStatus) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White), elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier.weight(1f)) {
                Text(status.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text("تاريخ الطلب: 2025-12-27", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
            }
            Surface(color = status.statusColor.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp)) {
                Text(text = status.status, color = status.statusColor, style = MaterialTheme.typography.labelSmall, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontWeight = FontWeight.Bold)
            }
        }
    }
}

data class RequestOption(val title: String, val subtitle: String, val icon: ImageVector, val bgTint: Color, val iconTint: Color)
data class RequestStatus(val title: String, val status: String, val statusColor: Color)