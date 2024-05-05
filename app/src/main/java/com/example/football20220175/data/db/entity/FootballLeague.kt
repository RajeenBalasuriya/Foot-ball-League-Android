package com.example.football20220175.data.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class FootballLeague(

    @PrimaryKey
    @NonNull
    val idLeague: String,
    val strLeague: String?,
    val strSport: String?,
    val strLeagueAlternate: String?

)
