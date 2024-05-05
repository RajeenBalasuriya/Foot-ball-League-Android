package com.example.football20220175.presentation.ui.clubsByLeagueScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.football20220175.data.db.dao.FootballClubDao

import com.example.football20220175.data.db.entity.FootballClub
import com.example.football20220175.data.network.searchClubsByLeagues
import com.example.football20220175.presentation.ui.components.CustomButton
import com.example.football20220175.presentation.ui.components.StyledTextField
import kotlinx.coroutines.launch


@Composable
fun ClubsByLeagueScreen(clubDao: FootballClubDao) {
    var leagueName by remember { mutableStateOf("") }
    var clubs by remember { mutableStateOf<List<FootballClub>>(emptyList()) }
    var isRequestSucessful by remember { mutableStateOf(false) }
    var hasRequestedAtLeastOnce by remember { mutableStateOf(false) }

    //creates a Coroutine scope bound to the lifecycle of the composable
    val scope= rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StyledTextField(labelText = "Enter football league name") {
            leagueName = it // Update the screen variable with the value of the text field
        }

        Spacer(modifier = Modifier.height(16.dp))
        //Retreive Clubs Buttons
        CustomButton(text = "Retrieve Clubs", width = 200,onClick = {
            scope.launch {
                clubs = searchClubsByLeagues(leagueName) // Make the api request and update the teams list with the result
                isRequestSucessful = clubs.isNotEmpty() // Update the flag based on if teams are found or not
                hasRequestedAtLeastOnce = true // Mark that a request has been made at least once
            }
        })

        //Save Clubs to Database
        CustomButton(text = "Save clubs to Database", width = 200,onClick = {
            scope.launch {
                clubDao.insertAll(clubs)

            }
        })

        // Display the retrieved teams in a scrollable list if the api request is successful
        if (hasRequestedAtLeastOnce) {
            if (isRequestSucessful) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(clubs) { team ->
                        TeamItem(team = team)
                    }
                }
            } else {
                Text(text = "No teams found for the above enter league ")
            }
        }
    }
}
@Composable
fun TeamItem(team: FootballClub) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "idName: ${team.idTeam}")
        Text(text = "                                     ")
        Text(text = "Name: ${team.Name}")
        Text(text = "                                     ")
        Text(text = "strTeamShort: ${team.strTeamShort}")
        Text(text = "                                     ")
        Text(text = "strAlternate: ${team.strAlternate}")
        Text(text = "                                     ")
        Text(text = "intFormedYear: ${team.intFormedYear}")
        Text(text = "                                     ")
        Text(text = "strLeague: ${team.strLeague}")
        Text(text = "                                     ")
        Text(text = "idLeague: ${team.idLeague}")
        Text(text = "                                     ")
        Text(text = "strStadium: ${team.strStadium}")
        Text(text = "                                     ")
        Text(text = "strKeywords: ${team.strKeywords}")
        Text(text = "                                     ")
        Text(text = "strStadiumLocation: ${team.strStadiumLocation}")
        Text(text = "                                     ")
        Text(text = "intStadiumCapacity: ${team.intStadiumCapacity}")
        Text(text = "                                     ")
        Text(text = "strWebsite: ${team.strWebsite}")
        Text(text = "                                     ")
        Text(text = "strTeamJersey: ${team.strTeamJersey}")
        Text(text = "                                     ")
        Text(text = "strTeamLogo: ${team.strTeamLogo}")
        Text(text = "                                     ")
        Text(text = "**** NEXT TEAM ****")

    }
}