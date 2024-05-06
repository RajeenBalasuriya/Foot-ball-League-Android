package com.example.football20220175.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StyledTextField(labelText: String, onValueChanged: (String) -> Unit) {
    var textValue by rememberSaveable { mutableStateOf("") }

    TextField(
        value = textValue,
        onValueChange = {
            textValue = it // Update the text value when the user types
            onValueChanged(it) // Call the callback with the current text
        },
        label = { Text(text = labelText) },
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(40.dp)
    )
}