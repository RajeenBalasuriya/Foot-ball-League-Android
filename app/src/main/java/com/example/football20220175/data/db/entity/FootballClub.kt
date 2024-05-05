package com.example.football20220175.data.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(tableName = "clubs",
//    foreignKeys = [ForeignKey(
//        entity = FootballLeague::class,
//        parentColumns = ["idLeague"],
//        childColumns = ["idTeam"],
//        onDelete = ForeignKey.CASCADE
//    )]
)
data class FootballClub(
    @PrimaryKey
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