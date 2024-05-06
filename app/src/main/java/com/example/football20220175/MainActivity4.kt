package com.example.football20220175

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.football20220175.presentation.ui.searchJersyScreen.JerseyByNameScreen
import com.example.football20220175.ui.theme.FootBall20220175Theme

class MainActivity4 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FootBall20220175Theme {
                // A surface container using the 'background' color from the theme
                JerseyByNameScreen()
                }
            }
        }
    }


