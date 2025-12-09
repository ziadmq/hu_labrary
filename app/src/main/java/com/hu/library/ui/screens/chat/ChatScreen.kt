package com.hu.library.ui.screens.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ChatScreen() {

    // Dummy messages (مؤقتاً)
    var messages by remember {
        mutableStateOf(
            listOf(
                ChatMessage("مرحبا، كيف يمكنني مساعدتك اليوم؟", isFromLibrarian = true),
                ChatMessage("بدي أعرف مكان رف هندسة البرمجيات", isFromLibrarian = false),
                ChatMessage("تفضل، موجود في الطابق الأول، يمين المدخل", isFromLibrarian = true)
            )
        )
    }

    var userInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {

        Text(
            text = "محادثة مع أمين المكتبة",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // ===== Messages List ===== //
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            reverseLayout = true
        ) {
            items(messages.reversed()) { msg ->
                ChatBubble(message = msg)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // ===== Input Field ===== //
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = userInput,
                onValueChange = { userInput = it },
                placeholder = { Text("اكتب رسالتك…") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (userInput.isNotEmpty()) {
                        messages = messages + ChatMessage(userInput, isFromLibrarian = false)
                        userInput = ""
                    }
                }
            ) {
                Text("إرسال")
            }
        }
    }
}
data class ChatMessage(
    val text: String,
    val isFromLibrarian: Boolean
)
@Composable
fun ChatBubble(message: ChatMessage) {

    val bubbleColor = if (message.isFromLibrarian)
        MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
    else
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)

    val alignment = if (message.isFromLibrarian)
        Arrangement.Start else Arrangement.End

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = alignment
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = bubbleColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
                .widthIn(max = 260.dp)
        ) {
            Text(
                text = message.text,
                color = Color.Black
            )
        }
    }
}