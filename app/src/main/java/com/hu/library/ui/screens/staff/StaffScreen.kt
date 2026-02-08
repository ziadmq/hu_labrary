package com.hu.library.ui.screens.staff

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.hu.library.data.model.Staff
import com.hu.library.data.model.staffList
import com.hu.library.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffScreen(navController: NavHostController) {
    var selectedTab by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    // حالة الموظف المختار لعرض تفاصيله
    var selectedStaffForDetails by remember { mutableStateOf<Staff?>(null) }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color(0xFFF8F9FA),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("الكادر الإداري", fontWeight = FontWeight.ExtraBold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // قسم البحث والتبديل
            Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth().shadow(4.dp, CircleShape),
                    placeholder = { Text("ابحث بالاسم أو القسم...", fontSize = 14.sp) },
                    leadingIcon = { Icon(Icons.Rounded.Search, null, tint = PrimaryColor) },
                    shape = CircleShape,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = PrimaryColor.copy(alpha = 0.7f),
                        unfocusedBorderColor = Color.Transparent
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth().background(Color(0xFFE9ECEF), CircleShape).padding(4.dp)
                ) {
                    ModernTabItem("القائمة", Icons.Rounded.FormatListBulleted, selectedTab == 0, Modifier.weight(1f)) { selectedTab = 0 }
                    ModernTabItem("الهيكل", Icons.Rounded.AccountTree, selectedTab == 1, Modifier.weight(1f)) { selectedTab = 1 }
                }
            }

            Box(modifier = Modifier.weight(1f)) {
                if (selectedTab == 0) {
                    StaffListView(staffList, searchQuery) { staff ->
                        selectedStaffForDetails = staff
                        showBottomSheet = true
                    }
                } else {
                    StaffStructureView(staffList) { staff ->
                        selectedStaffForDetails = staff
                        showBottomSheet = true
                    }
                }
            }
        }

        // 🟢 نافذة تفاصيل الموظف (Bottom Sheet)
        if (showBottomSheet && selectedStaffForDetails != null) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
                dragHandle = { BottomSheetDefaults.DragHandle(color = PrimaryColor.copy(0.2f)) }
            ) {
                StaffDetailContent(selectedStaffForDetails!!)
            }
        }
    }
}

@Composable
fun StaffDetailContent(staff: Staff) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // صورة كبيرة للموظف
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(PrimaryColor.copy(0.1f))
        ) {
            AsyncImage(
                model = staff.imageUrl,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(staff.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(staff.position, color = PrimaryColor, fontWeight = FontWeight.Medium, fontSize = 16.sp)

        Surface(
            modifier = Modifier.padding(vertical = 12.dp),
            color = Color(0xFFF1F3F5),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(staff.department, modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp), fontSize = 13.sp, color = Color.Gray)
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color(0xFFEEEEEE))

        // تفاصيل الاتصال
        DetailRow(Icons.Rounded.Phone, "رقم الهاتف", staff.phone)
        DetailRow(Icons.Rounded.Email, "البريد الإلكتروني", staff.email)
        if (staff.officeLocation.isNotEmpty()) {
            DetailRow(Icons.Rounded.LocationOn, "موقع المكتب", staff.officeLocation)
        }

        Spacer(modifier = Modifier.height(30.dp))

        // أزرار إجراءات سريعة
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                onClick = { context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:${staff.phone}"))) },
                modifier = Modifier.weight(1f).height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2ECC71)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Rounded.Call, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("اتصال هاتفي")
            }

            Button(
                onClick = { context.startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${staff.email}"))) },
                modifier = Modifier.weight(1f).height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Rounded.Mail, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("مراسلة")
            }
        }
    }
}

@Composable
fun DetailRow(icon: ImageVector, label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = PrimaryColor, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(label, fontSize = 11.sp, color = Color.Gray)
            Text(value, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        }
    }
}

// تعديل الـ ListView ليدعم الضغط
@Composable
fun StaffListView(staffList: List<Staff>, searchQuery: String, onStaffClick: (Staff) -> Unit) {
    val filtered = staffList.filter { it.name.contains(searchQuery, true) || it.department.contains(searchQuery, true) }
    LazyColumn(contentPadding = PaddingValues(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(filtered) { staff ->
            ModernStaffCard(staff, onClick = { onStaffClick(staff) })
        }
    }
}

@Composable
fun ModernStaffCard(staff: Staff, onClick: () -> Unit) {
    Card(
        onClick = onClick, // تفعيل الضغط على الكرت
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = staff.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(CircleShape).background(PrimaryColor.copy(0.1f)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(staff.name, fontWeight = FontWeight.Bold, color = Color(0xFF2D3436))
                Text(staff.position, style = MaterialTheme.typography.bodySmall, color = PrimaryColor)
            }
            Icon(Icons.Rounded.ChevronLeft, null, tint = Color.LightGray)
        }
    }
}

// تعديل الـ StructureView ليدعم الضغط
@Composable
fun StaffStructureView(staffList: List<Staff>, onStaffClick: (Staff) -> Unit) {
    val root = staffList.find { it.supervisorId == null } ?: staffList.firstOrNull()
    Box(modifier = Modifier.fillMaxSize().horizontalScroll(rememberScrollState()).verticalScroll(rememberScrollState()).padding(24.dp), contentAlignment = Alignment.TopCenter) {
        if (root != null) SmoothOrgTree(root, staffList, onStaffClick)
    }
}

@Composable
fun SmoothOrgTree(staff: Staff, allStaff: List<Staff>, onStaffClick: (Staff) -> Unit) {
    val children = allStaff.filter { it.supervisorId == staff.id }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            onClick = { onStaffClick(staff) }, // تفعيل الضغط في الهيكل
            modifier = Modifier.width(150.dp).shadow(8.dp, RoundedCornerShape(16.dp)),
            color = if (staff.supervisorId == null) PrimaryColor else Color.White,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(model = staff.imageUrl, contentDescription = null, modifier = Modifier.size(40.dp).clip(CircleShape), contentScale = ContentScale.Crop)
                Text(staff.name.split(" ").take(2).joinToString(" "), fontWeight = FontWeight.Bold, fontSize = 12.sp, color = if (staff.supervisorId == null) Color.White else Color.Black)
            }
        }
        if (children.isNotEmpty()) {
            Box(modifier = Modifier.height(20.dp).width(2.dp).background(Color.LightGray))
            Row {
                children.forEach { child -> SmoothOrgTree(child, allStaff, onStaffClick); Spacer(modifier = Modifier.width(16.dp)) }
            }
        }
    }
}

@Composable
fun ModernTabItem(text: String, icon: ImageVector, isSelected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Surface(
        onClick = onClick,
        modifier = modifier.height(40.dp).shadow(if (isSelected) 4.dp else 0.dp, CircleShape),
        color = if (isSelected) Color.White else Color.Transparent,
        shape = CircleShape
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(icon, null, modifier = Modifier.size(18.dp), tint = if (isSelected) PrimaryColor else Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text, color = if (isSelected) PrimaryColor else Color.Gray, fontWeight = FontWeight.Bold, fontSize = 13.sp)
        }
    }
}