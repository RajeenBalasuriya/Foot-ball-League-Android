package com.example.football20220175.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football20220175.data.db.entity.FootballLeague

@Dao
interface FootballLeagueDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeagues(leagues: List<FootballLeague>)
    @Query("SELECT * FROM leagues")
    fun getAllLeagues(): List<FootballLeague>
}
