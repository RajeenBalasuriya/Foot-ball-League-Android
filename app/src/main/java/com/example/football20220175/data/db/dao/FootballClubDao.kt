package com.example.football20220175.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.football20220175.data.db.entity.FootballClub


@Dao
interface FootballClubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(clubs: FootballClub)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clubs: List<FootballClub>)

    @Query("SELECT * FROM clubs WHERE name LIKE '%' || :searchQuery || '%'")
    fun searchClubsByName(searchQuery: String): List<FootballClub>


}