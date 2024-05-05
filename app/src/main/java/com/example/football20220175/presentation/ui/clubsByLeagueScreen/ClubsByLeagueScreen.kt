package com.example.football20220175.presentation.ui.clubsByLeagueScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.football20220175.presentation.ui.components.CustomButton
import com.example.football20220175.presentation.ui.components.StyledTextField


@Composable
fun ClubsByLeagueScreen() {
    var leagueName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StyledTextField(labelText = "Enter football league name") {
            leagueName = it // Update the screen variable with the value of the text field
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = "Retrieve Clubs", width = 200,onClick = { println("----------------------------"+leagueName) })
        Spacer(modifier = Modifier.height(8.dp))

        CustomButton(text = "Save clubs to Database", width = 200,onClick = { /*TODO*/ })
    }
}
