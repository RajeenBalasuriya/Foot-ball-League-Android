package com.example.football20220175.presentation.ui.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    text: String,
    width: Int ,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.width(width=width.dp),
        onClick = onClick
    ) {
        Text(text = text)
    }
}