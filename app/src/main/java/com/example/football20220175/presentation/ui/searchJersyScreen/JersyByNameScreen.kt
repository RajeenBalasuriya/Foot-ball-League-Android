package com.example.football20220175.presentation.ui.searchJersyScreen



import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.football20220175.data.db.entity.FootballClub
import com.example.football20220175.data.network.Jersey
import com.example.football20220175.data.network.searchClubsByPartialName
import com.example.football20220175.data.network.searchJerseysById
import com.example.football20220175.presentation.ui.components.CustomButton
import com.example.football20220175.presentation.ui.components.StyledTextField
import kotlinx.coroutines.launch

@Composable
fun JerseyByNameScreen() {
    var searchName by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var clubs by remember { mutableStateOf<List<FootballClub>>(emptyList()) }
    var jerseys by remember { mutableStateOf<List<Jersey>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        StyledTextField(labelText = "Enter part of a club name") {
            searchName = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(text = "Search Jerseys", width = 200, onClick = {
            coroutineScope.launch {
                clubs= emptyList()
                jerseys= emptyList()
                clubs = searchClubsByPartialName(searchName)
               jerseys= clubs.flatMap { club ->
                    searchJerseysById(club.idTeam)}

        }
        })


        Spacer(modifier = Modifier.height(16.dp))

        DisplayJerseyResults(jerseys,clubs)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DisplayJerseyResults(jerseys: List<Jersey>,clubs:List<FootballClub>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(jerseys) { jersey ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Load and display jersey image
                Image(
                    painter = rememberImagePainter(jersey.strEquipment),
                    contentDescription = "Jersey Image",
                    modifier = Modifier.size(50.dp),
                )

                // Display team name
                Text(
                    text = jersey.strUsername, // Adjust this to display the team name instead of team ID
                    modifier = Modifier.padding(start = 16.dp)
                )

                clubs.forEach(){
                    if(it.idTeam==jersey.idTeam){
                        Text(
                            text = it.name, // Adjust this to display the team name instead of team ID
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

            }
        }
    }
}