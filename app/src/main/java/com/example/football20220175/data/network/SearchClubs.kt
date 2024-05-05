package com.example.football20220175.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// Define a data class to represent the structure of the JSON objects
data class Team(
    val idTeam: String,
    val Name: String,
    val strTeamShort: String,
    val strAlternate: String,
    val intFormedYear: String,
    val strLeague: String,
    val idLeague: String,
    val strStadium: String,
    val strKeywords: String,
    val strStadiumThumb: String,
    val strStadiumLocation: String,
    val intStadiumCapacity: String,
    val strWebsite: String,
    val strTeamJersey: String,
    val strTeamLogo: String
)

suspend fun searchClubsByLeagues(keyword: String): List<Team> {
    try {
        val url_string = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=$keyword"
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        var teamsList = mutableListOf<Team>()

        withContext(Dispatchers.IO) {
            val bf = BufferedReader(InputStreamReader(con.inputStream))
            val jsonString = bf.readText()

            // Parse JSON response
            val response = JSONObject(jsonString)
            val teamsArray = response.getJSONArray("teams")

            // Iterate through the array and extract team information
            for (i in 0 until teamsArray.length()) {
                val teamObject = teamsArray.getJSONObject(i)
                teamsList.add(
                    Team(
                        idTeam = teamObject.getString("idTeam"),
                        Name = teamObject.getString("strTeam"),
                        strTeamShort = teamObject.getString("strTeamShort"),
                        strAlternate = teamObject.getString("strAlternate"),
                        intFormedYear = teamObject.getString("intFormedYear"),
                        strLeague = teamObject.getString("strLeague"),
                        idLeague = teamObject.getString("idLeague"),
                        strStadium = teamObject.getString("strStadium"),
                        strKeywords = teamObject.getString("strKeywords"),
                        strStadiumThumb = teamObject.getString("strStadiumThumb"),
                        strStadiumLocation = teamObject.getString("strStadiumLocation"),
                        intStadiumCapacity = teamObject.getString("intStadiumCapacity"),
                        strWebsite = teamObject.getString("strWebsite"),
                        strTeamJersey = teamObject.getString("strTeamJersey"),
                        strTeamLogo = teamObject.getString("strTeamLogo")
                    )
                )
            }
        }
        return teamsList
    } catch (e: Exception) {
        e.printStackTrace()
        return emptyList()
    }
}



