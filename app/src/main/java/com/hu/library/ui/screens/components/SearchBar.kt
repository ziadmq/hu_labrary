package com.hu.library.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(hint: String, onSearch: (String) -> Unit) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(hint) }
    )
}
