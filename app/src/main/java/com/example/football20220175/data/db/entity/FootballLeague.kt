package com.example.football20220175.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class FootballLeague(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val idLeague: String?,
    val strLeague: String?,
    val strSport: String?,
    val strLeagueAlternate: String?

)
