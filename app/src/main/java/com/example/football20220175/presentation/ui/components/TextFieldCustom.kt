package com.example.football20220175.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun StyledTextField(labelText: String, onValueChanged: (String) -> Unit) {
    var value by remember { mutableStateOf(TextFieldValue("")) }

    TextField(
        value = value,
        onValueChange = {
            value = it // Update the value when the user types
            onValueChanged(it.text) // Call the callback with the current text
        },
        label = { Text(text = labelText) },
        maxLines = 2,
        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(40.dp)

    )
}