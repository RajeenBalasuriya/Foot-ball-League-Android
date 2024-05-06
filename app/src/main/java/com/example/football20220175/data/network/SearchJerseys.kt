package com.example.football20220175.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

data class Jersey(val idEquipment: String, val idTeam: String, val date: String, val strSeason: String,val strEquipment: String,val strType:String,val strUsername:String)
val jerseys = mutableListOf<Jersey>()
suspend fun searchJerseysById(teamId: String): List<Jersey> {
    return withContext(Dispatchers.IO) {
        try {
            val baseUrl = "https://www.thesportsdb.com/api/v1/json/3/"
            val jerseyUrl = "${baseUrl}lookupequipment.php?id=$teamId"
            val response = URL(jerseyUrl).readText()
            parseJerseyResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}

fun parseJerseyResponse(response: String): List<Jersey> {

    try {
        val json = JSONObject(response)
        val equipmentArray = json.getJSONArray("equipment")
        for (i in 0 until equipmentArray.length()) {
            val equipmentObject = equipmentArray.getJSONObject(i)
            val id = equipmentObject.getString("idEquipment")
            val teamId = equipmentObject.getString("idTeam")
            val date = equipmentObject.getString("date")
            val season = equipmentObject.getString("strSeason")
            val url = equipmentObject.getString("strEquipment")
            val type = equipmentObject.getString("strType")
            val username = equipmentObject.getString("strUsername")
            jerseys.add(Jersey(id, teamId,date, season, url,type,username))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return jerseys
}