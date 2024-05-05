package com.example.football20220175.presentation.ui.searchForClubsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.example.football20220175.data.db.dao.FootballLeagueDao
import com.example.football20220175.data.db.database.FootballDatabase
import com.example.football20220175.data.db.entity.FootballClub
import com.example.football20220175.data.db.entity.FootballLeague
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
    var searchName by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<FootballClub>>(emptyList()) }
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

        CustomButton(text = "Search", width = 200, onClick = {
            performSearch()
        })

        Spacer(modifier = Modifier.height(16.dp))

        // Call DisplaySearchResults to display search results
        DisplaySearchResults(searchResults)
    }
}

@Composable
fun DisplaySearchResults(searchResults: List<FootballClub>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            items(searchResults) { club ->
                Text(text = "Club: ${club.name}")
            }
        }
    }
}

