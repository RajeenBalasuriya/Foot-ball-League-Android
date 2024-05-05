package com.example.football20220175.data.network

import com.example.football20220175.data.db.entity.FootballClub
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// A data class to represent the structure of the JSON objects


suspend fun searchClubsByLeagues(keyword: String): List<FootballClub> {
    try {
        val url_string = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=$keyword"
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        var teamsList = mutableListOf<FootballClub>()

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
                    FootballClub(
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



