package com.example.football20220175.presentation.ui.mainScreen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.football20220175.MainActivity2
import com.example.football20220175.data.db.dao.FootballLeagueDao
import com.example.football20220175.data.db.database.FootballDatabase
import com.example.football20220175.presentation.ui.clubsByLeagueScreen.ClubsByLeagueScreen
import com.example.football20220175.presentation.ui.components.CustomButton
import com.example.football20220175.presentation.ui.mainScreen.buttonControllers.addLeague
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun MainScreen(

    footballDatabase: FootballDatabase,
    footballDao: FootballLeagueDao
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current



    Column(modifier = Modifier.padding(16.dp)) {
        CustomButton(
            text = "Add Leagues To Db",
            width = 250,
            onClick = {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {

                        addLeague(footballDatabase, footballDao)
                    }
                }
            }
        )

        CustomButton(text = "Search for clubs by league", width = 250, onClick = {
        val i =Intent(context, MainActivity2::class.java)
            context.startActivity(i)

        })
        CustomButton(text = "Show Leagues2", width = 250, onClick = { /*TODO*/ })


    }
}

