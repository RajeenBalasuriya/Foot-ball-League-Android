package com.example.football20220175

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.football20220175.data.db.database.FootballDatabase
import com.example.football20220175.presentation.ui.clubsByLeagueScreen.ClubsByLeagueScreen
import com.example.football20220175.ui.theme.FootBall20220175Theme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FootBall20220175Theme {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,

                ) {
                   var FootballDatabase= FootballDatabase.getInstance(applicationContext)
                    val clubDao = FootballDatabase.clubDao()
                    ClubsByLeagueScreen(clubDao)
                }

            }
        }
    }
}

