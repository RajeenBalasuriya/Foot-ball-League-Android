package com.example.football20220175.presentation.ui.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.football20220175.data.db.dao.FootballLeagueDao
import com.example.football20220175.data.db.database.FootballDatabase
import com.example.football20220175.presentation.ui.mainScreen.buttonControllers.addLeague
import com.example.football20220175.presentation.ui.mainScreen.components.CustomButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun MainScreen(
    footballDatabase: FootballDatabase,
    footballDao: FootballLeagueDao
) {
    val coroutineScope = rememberCoroutineScope()



    Column(modifier = Modifier.padding(16.dp)) {
        CustomButton(
            text = "Add Leagues To Db",
            onClick = {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {

                        addLeague(footballDatabase, footballDao)
                    }
                }
            }
        )

        CustomButton(text = "Show Leagues", onClick = { /*TODO*/ })
        CustomButton(text = "Show Leagues2", onClick = { /*TODO*/ })


    }
}
