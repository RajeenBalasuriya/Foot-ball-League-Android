package com.example.football20220175.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.football20220175.data.db.dao.FootballClubDao
import com.example.football20220175.data.db.dao.FootballLeagueDao
import com.example.football20220175.data.db.entity.FootballClub
import com.example.football20220175.data.db.entity.FootballLeague

@Database(entities = [FootballLeague::class, FootballClub::class], version = 1, exportSchema = false)
abstract class FootballDatabase : RoomDatabase() {
    abstract fun leagueDao(): FootballLeagueDao
    abstract  fun clubDao(): FootballClubDao


    companion object {
        @Volatile
        private var INSTANCE: FootballDatabase? = null

        fun getInstance(context: Context): FootballDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FootballDatabase::class.java,
                    "football_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

    // Method to drop the schema by deleting the database file
    fun dropSchema(context: Context) {
        val dbFile = context.getDatabasePath("football_database")
        if (dbFile.exists()) {
            dbFile.delete()
        }
    }
}
