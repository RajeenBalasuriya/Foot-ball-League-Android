package com.example.football20220175

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.football20220175.data.db.database.FootballDatabase
import com.example.football20220175.presentation.ui.searchForClubsScreen.SearchClubScreen
import com.example.football20220175.ui.theme.FootBall20220175Theme

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FootBall20220175Theme {
                var FootballDatabase= FootballDatabase.getInstance(applicationContext)
                val clubDao = FootballDatabase.clubDao()
                val leagueDao = FootballDatabase.leagueDao()
                    SearchClubScreen(FootballDatabase,clubDao,leagueDao)
                }
            }
        }
    }


