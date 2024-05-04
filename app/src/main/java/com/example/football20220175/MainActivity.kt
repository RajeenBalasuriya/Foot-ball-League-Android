package com.example.football20220175

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    ScreenMain()
                }
            }
        }
    }
}

@Composable
fun ScreenMain(){
    Column(modifier=Modifier.padding(16.dp)

    ) {

        Button(modifier = Modifier.width(250.dp), onClick = { /*TODO*/ }) {
            Text(text = "Add Leagues To DB")

        }


        Button(modifier = Modifier.width(250.dp),onClick = { /*TODO*/ }) {
            Text(text = "Search for Clubs By League")

        }


        Button(modifier = Modifier.width(250.dp),onClick = { /*TODO*/ }) {
            Text(text = "Search for Clubs")

        }

    }
}