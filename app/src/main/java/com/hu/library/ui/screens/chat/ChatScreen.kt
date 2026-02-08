package com.hu.library.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hu.library.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    onBackClick: () -> Unit = {}
) {
    // Dummy Data (In a real app, this comes from ViewModel)
    val initialMessages = listOf(
        ChatMessage("1", "مرحباً زياد 👋", false, "10:00 AM"),
        ChatMessage("2", "كيف يمكنني مساعدتك اليوم في مكتبة الجامعة الهاشمية؟", false, "10:00 AM"),
        ChatMessage("3", "أريد الاستفسار عن كتاب 'Introduction to Algorithms'", true, "10:01 AM"),
        ChatMessage("4", "لحظة من فضلك، جاري البحث في الفهرس... 🔍", false, "10:01 AM"),
        ChatMessage("5", "الكتاب متوفر في الطابق الثاني، رف رقم 4B.", false, "10:02 AM")
    )

    var messages by remember { mutableStateOf(initialMessages) }
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()

    // Auto-scroll to bottom when a new message arrives
    LaunchedEffect(messages.size) {
        listState.animateScrollToItem(messages.size - 1)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "المساعد الذكي",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            "متصل الآن 🟢",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = PrimaryColor // HU Red
                )
            )
        },
        containerColor = BackgroundColor, // Light Grey
        bottomBar = {
            ChatInputBar(
                value = inputText,
                onValueChange = { inputText = it },
                onSend = {
                    if (inputText.isNotBlank()) {
                        // Add User Message
                        messages = messages + ChatMessage(
                            id = System.currentTimeMillis().toString(),
                            text = inputText,
                            isMe = true,
                            time = "الآن"
                        )
                        inputText = ""

                        // Simulate Bot Response (Optional for Demo)
                        // In real app, ViewModel handles this
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(messages) { message ->
                MessageBubble(message)
            }
        }
    }
}

// ===== Components =====

@Composable
fun MessageBubble(message: ChatMessage) {
    val isMe = message.isMe

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        // Bot Avatar (Left Side)
        if (!isMe) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.SmartToy,
                    contentDescription = "Bot",
                    tint = PrimaryColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

        // The Chat Bubble
        Column(
            horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
        ) {
            Surface(
                // Rounded corners logic: "Point" towards the sender
                shape = if (isMe) RoundedCornerShape(20.dp, 4.dp, 20.dp, 20.dp)
                else RoundedCornerShape(4.dp, 20.dp, 20.dp, 20.dp),
                color = if (isMe) PrimaryColor else Color.White,
                shadowElevation = 2.dp,
                modifier = Modifier.widthIn(max = 280.dp)
            ) {
                Text(
                    text = message.text,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                    color = if (isMe) Color.White else TextPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            // Timestamp
            Text(
                text = message.time,
                style = MaterialTheme.typography.labelSmall,
                color = TextSecondary,
                fontSize = 10.sp
            )
        }

        // User Avatar (Right Side)
        if (isMe) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(SecondaryColor), // Gold background
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Me",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInputBar(
    value: String,
    onValueChange: (String) -> Unit,
    onSend: () -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 12.dp,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Text Field
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = { Text("اكتب رسالتك هنا...", fontSize = 14.sp, color = Color.LightGray) },
                modifier = Modifier
                    .weight(1f)
                    .heightIn(min = 50.dp),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedContainerColor = BackgroundColor,
                    unfocusedContainerColor = BackgroundColor
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { onSend() }),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Send Button
            FloatingActionButton(
                onClick = onSend,
                containerColor = PrimaryColor,
                contentColor = Color.White,
                shape = CircleShape,
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    modifier = Modifier.size(22.dp).offset(x = 2.dp) // Slight offset for visual center
                )
            }
        }
    }
}

// Data Model for Messages
data class ChatMessage(
    val id: String,
    val text: String,
    val isMe: Boolean,
    val time: String
)