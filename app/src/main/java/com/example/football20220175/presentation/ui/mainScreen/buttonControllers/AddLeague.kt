package com.example.football20220175.presentation.ui.mainScreen.buttonControllers

import com.example.football20220175.data.db.dao.FootballLeagueDao
import com.example.football20220175.data.db.database.FootballDatabase
import com.example.football20220175.data.db.entity.FootballLeague
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

//this the logic for controller of the button Add Leagues To DB

fun addLeague(FootballDatabase: FootballDatabase, footballDao: FootballLeagueDao) {
    val jsonString = """
    {
       "leagues":[
          {
             "idLeague":"4328",
             "strLeague":"English Premier League",
             "strSport":"Soccer",
             "strLeagueAlternate":"Premier League, EPL"
          },
          {
             "idLeague":"4329",
             "strLeague":"English League Championship",
             "strSport":"Soccer",
             "strLeagueAlternate":"Championship"
          },
          {
             "idLeague":"4330",
             "strLeague":"Scottish Premier League",
             "strSport":"Soccer",
             "strLeagueAlternate":"Scottish Premiership, SPFL"
          },
          {
             "idLeague":"4331",
             "strLeague":"German Bundesliga",
             "strSport":"Soccer",
             "strLeagueAlternate":"Bundesliga, Fußball-Bundesliga"
          },
          {
             "idLeague":"4332",
             "strLeague":"Italian Serie A",
             "strSport":"Soccer",
             "strLeagueAlternate":"Serie A"
          },
          {
             "idLeague":"4334",
             "strLeague":"French Ligue 1",
             "strSport":"Soccer",
             "strLeagueAlternate":"Ligue 1 Conforama"
          },
          {
             "idLeague":"4335",
             "strLeague":"Spanish La Liga",
             "strSport":"Soccer",
             "strLeagueAlternate":"LaLiga Santander, La Liga"
          },
          {
             "idLeague":"4336",
             "strLeague":"Greek Superleague Greece",
             "strSport":"Soccer",
             "strLeagueAlternate":""
          },
          {
             "idLeague":"4337",
             "strLeague":"Dutch Eredivisie",
             "strSport":"Soccer",
             "strLeagueAlternate":"Eredivisie"
          },
          {
             "idLeague":"4338",
             "strLeague":"Belgian First Division A",
             "strSport":"Soccer",
             "strLeagueAlternate":"Jupiler Pro League"
          },
          {
             "idLeague":"4339",
             "strLeague":"Turkish Super Lig",
             "strSport":"Soccer",
             "strLeagueAlternate":"Super Lig"
          },
          {
             "idLeague":"4340",
             "strLeague":"Danish Superliga",
             "strSport":"Soccer",
             "strLeagueAlternate":""
          },
          {
             "idLeague":"4344",
             "strLeague":"Portuguese Primeira Liga",
             "strSport":"Soccer",
             "strLeagueAlternate":"Liga NOS"
          },
          {
             "idLeague":"4346",
             "strLeague":"American Major League Soccer",
             "strSport":"Soccer",
             "strLeagueAlternate":"MLS, Major League Soccer"
          },
          {
             "idLeague":"4347",
             "strLeague":"Swedish Allsvenskan",
             "strSport":"Soccer",
             "strLeagueAlternate":"Fotbollsallsvenskan"
          },
          {
             "idLeague":"4350",
             "strLeague":"Mexican Primera League",
             "strSport":"Soccer",
             "strLeagueAlternate":"Liga MX"
          },
          {
             "idLeague":"4351",
             "strLeague":"Brazilian Serie A",
             "strSport":"Soccer",
             "strLeagueAlternate":""
          },
          {
             "idLeague":"4354",
             "strLeague":"Ukrainian Premier League",
             "strSport":"Soccer",
             "strLeagueAlternate":""
          },
          {
             "idLeague":"4355",
             "strLeague":"Russian Football Premier League",
             "strSport":"Soccer",
             "strLeagueAlternate":"Чемпионат России по футболу"
          },
          {
             "idLeague":"4356",
             "strLeague":"Australian A-League",
             "strSport":"Soccer",
             "strLeagueAlternate":"A-League"
          },
          {
             "idLeague":"4358",
             "strLeague":"Norwegian Eliteserien",
             "strSport":"Soccer",
             "strLeagueAlternate":"Eliteserien"
          },
          {
             "idLeague":"4359",
             "strLeague":"Chinese Super League",
             "strSport":"Soccer",
             "strLeagueAlternate":""
          }
       ]
    }

""".trimIndent()

    val leaguesJsonArray = JSONObject(jsonString).getJSONArray("leagues")
    val leaguesList = mutableListOf<FootballLeague>()

    for (i in 0 until leaguesJsonArray.length()) {
        val leagueObject = leaguesJsonArray.getJSONObject(i)
        val league = FootballLeague(
            idLeague = leagueObject.getString("idLeague"),
            strLeague = leagueObject.getString("strLeague"),
            strSport = leagueObject.getString("strSport"),
            strLeagueAlternate = leagueObject.getString("strLeagueAlternate")
        )
        leaguesList.add(league)

    }

    try {
        footballDao.insertLeagues(leaguesList)
        println("Leagues inserted successfully")
    } catch (e: Exception) {
        println("Error occurred while inserting leagues: ${e.message}")

    }

}