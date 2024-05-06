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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
    var leagueName by rememberSaveable { mutableStateOf("") }
    var clubs by rememberSaveable { mutableStateOf<List<FootballClub>>(emptyList()) }
    var isRequestSuccessful by rememberSaveable { mutableStateOf(false) }
    var hasRequestedAtLeastOnce by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StyledTextField(labelText = "Enter football league name") {
            leagueName = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = "Retrieve Clubs", width = 200) {
            scope.launch {
                clubs = searchClubsByLeagues(leagueName)
                isRequestSuccessful = clubs.isNotEmpty()
                hasRequestedAtLeastOnce = true
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = "Save clubs to Database", width = 200) {
            scope.launch {
                clubDao.insertAll(clubs)
            }
        }

        if (hasRequestedAtLeastOnce) {
            if (isRequestSuccessful) {
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
                Text(text = "No teams found for the above entered league")
            }
        }
    }
}
@Composable
fun TeamItem(team: FootballClub) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "idName: ${team.idTeam}")
        Text(text = "                                     ")
        Text(text = "Name: ${team.name}")
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