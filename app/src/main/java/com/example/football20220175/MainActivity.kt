package com.example.football20220175

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.football20220175.data.db.database.FootballDatabase


import com.example.football20220175.presentation.ui.mainScreen.MainScreen

import com.example.football20220175.ui.theme.FootBall20220175Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootBall20220175Theme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {

                    val footballDatabase = FootballDatabase.getInstance(applicationContext)

                    val footballDao = footballDatabase.leagueDao()

                    MainScreen(footballDatabase, footballDao)



                }
            }
        }
    }
}

