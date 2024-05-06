package com.example.football20220175.presentation.ui.searchForClubsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.football20220175.data.db.dao.FootballClubDao
import com.example.football20220175.data.db.dao.FootballLeagueDao
import com.example.football20220175.data.db.database.FootballDatabase
import com.example.football20220175.data.db.entity.FootballClub
import com.example.football20220175.presentation.ui.components.CustomButton
import com.example.football20220175.presentation.ui.components.StyledTextField
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun SearchClubScreen(
    FootballDatabase: FootballDatabase,
    clubDao: FootballClubDao,
    leagueDao: FootballLeagueDao
) {
    var searchName by rememberSaveable { mutableStateOf("") }
    var searchResults by rememberSaveable { mutableStateOf<List<FootballClub>>(emptyList()) }
    val scope = rememberCoroutineScope()

    // Function to perform search on click of search button
    fun performSearch() {
        scope.launch {
            // Perform database operations in a coroutine scope
            searchResults = withContext(Dispatchers.IO) {
                clubDao.searchClubsByName(searchName)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StyledTextField(labelText = "part of a name of a club") {
            searchName = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = "Search", width = 200) {
            performSearch()
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Call DisplaySearchResults to display search results
        DisplaySearchResults(searchResults)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DisplaySearchResults(searchResults: List<FootballClub>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        // Add a header as a separate item
        item {
            Text(
                text = "Search Results",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(16.dp)
            )
        }

        // Main items
        items(searchResults) { club ->
            ClubItem(club)
        }
    }
}
@OptIn(ExperimentalCoilApi::class)
@Composable
fun ClubItem(club: FootballClub) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Load and display club logo image
        Image(
            painter = rememberImagePainter(club.strTeamLogo),
            contentDescription = "Club Logo",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit
        )

        // Display club name
        Text(
            text = club.name,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

